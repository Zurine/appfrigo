package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Contact;
import es.smt.appfrigo.bean.Distributor;
import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.RegionManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IDistributorService;
import es.smt.appfrigo.service.IRegionService;

@Controller
@RequestMapping("/distributor")
public class DistributorController {

	private String folder = "distributor";
	
	@Autowired
	private IDistributorService distributorService;
	
	@Autowired
	private IRegionService regionService;

	
	/*****************************************COMBOS****************************************************/
	@ModelAttribute("regionList")
	public List<Region> getRegion() {
	    return getRegionList();
	}
	
	private List<Region> getRegionList()
	{
		List<Region> list = new ArrayList<Region>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = regionService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					list = RegionManager.getInstance().getActive(ParseJSON.getInstance().getRegionDTOList(r.getResponse()));
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	/*****************************************COMBOS****************************************************/
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model ) {	
		
		List<Distributor> result = new ArrayList<Distributor>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = distributorService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getDistributorDTOList(r.getResponse());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("distributorList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model ) {	
		
		Distributor d = new Distributor();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			List<Contact> c = new ArrayList<Contact>();
			c.add(new Contact(0, ""));
			d.setContacts(c);
			model.addAttribute("distributor", d);
			model.addAttribute("title",	setTitle(d));
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Distributor distributor, BindingResult bindingResult,ModelMap model, RedirectAttributes redirectAttributes) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (bindingResult.hasErrors()) 
		{
			model.addAttribute(Constants.errormsg,
					ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
		}
		else
		{
			if(distributor.getRegion()==null || distributor.getRegion().getId() == 0)
			{
				model.addAttribute(Constants.errormsg, "Region must be selected"); 
			}
			else
			{
				 if(user!=null && user.isCredentialsNonExpired())
				 {
					try {
						ResponseDTO r = null;
						if(distributor.getId() == 0){
							r = distributorService.add(distributor, distributor.getContacts(), user.getToken());
						}
						else  r = distributorService.edit(distributor, distributor.getContacts(), user.getToken());
						
						if(r.getError().getCode() == Constants.codeOK)
						{
							redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully saved");
							
							if(distributor.isNext())
								return new ModelAndView("redirect:/"+folder+"/add");
							else return new ModelAndView("redirect:/"+folder+"/list");
						}
						else model.addAttribute(Constants.errormsg, r.getError().getDesc());
	
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else return new ModelAndView(Constants.logoutRedirect);
			 }
		}
		model.addAttribute("distributor", distributor);
		 
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {	
		
		Distributor distributor = null;
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = distributorService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					distributor = ParseJSON.getInstance().getDistributorDTO(r.getResponse());
					if(distributor.getContacts() == null || distributor.getContacts().size() == 0){
						distributor.setContacts(new ArrayList<Contact>());
						distributor.getContacts().add(new Contact());
					}
					model.addAttribute("distributor", distributor);
				}
				else
				{
					redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
					
					return new ModelAndView("redirect:/"+folder+"/list");
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		model.addAttribute("title",	setTitle(distributor));

		return new ModelAndView(folder+"/add");
	 }
	
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = distributorService.activate(state, id, user.getToken());
				if(r.getError().getCode() != Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = distributorService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.infomsg,"Successfully deleted");
				}
				else
				{
					boolean res = ParseJSON.getInstance().getBoolean(r.getResponse());
					if(!res)
						redirect.addFlashAttribute(Constants.errormsg,"You cannot delete this item");
					else redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/list");
	}
	
	
	@RequestMapping(value="/addItem") 
	public String addItem(final Distributor distributor, final BindingResult bindingResult,ModelMap model) {
		
		model.addAttribute("distributor", addDistributor(distributor));
		
	    return folder+"/add :: fragment-contact";
	}
	
	private Distributor addDistributor(Distributor d){

		d.getContacts().add( new Contact());

		
		return d;
	}
	
	
	@RequestMapping(value="/removeItem") // , params={"addRow"}
	public String removeItem(final Distributor 
			distributor, final BindingResult bindingResult,ModelMap model) {
		
		
		distributor.getContacts().remove(distributor.getRow());
		Distributor d = distributor;
		
		if(distributor.getContacts().size() == 0)
			d =	addDistributor(distributor);
		
		model.addAttribute("distributor", d);
	    return folder+"/add :: fragment-contact";
	}
	
	private String setTitle(Distributor d){
		String title = "";
		if(d.getId() == 0)
			title = "Create New Distributor";
		else title = "Edit " + d.getConcession();
		
		return title;
	}
	
	
}
