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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IRegionService;

@Controller
@RequestMapping("/region")
public class RegionController {

	private String folder = "region";
	
	@Autowired
	private IRegionService regionService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model ) {	
		
		List<Region> result = new ArrayList<Region>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = regionService.list(user.getToken());

				if(r.getError().getCode() == Constants.codeOK)
				{
					result =  ParseJSON.getInstance().getRegionDTOList(r.getResponse());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("regionList", result);
		
		return new ModelAndView(folder+"/list");
	 }
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		Region r = new Region();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			model.addAttribute("region", r);
			model.addAttribute("title",	setTitle(r));
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Region  region, BindingResult bindingResult,ModelMap model, RedirectAttributes redirectAttributes) {	
		
		 if (bindingResult.hasErrors()) 
		 {
			 model.addAttribute(Constants.errormsg,
					 ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
		 }
		 else
		 {
			UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user!=null && user.isCredentialsNonExpired())
			 {
			
				try {
					Region dto = ParseJSON.getInstance().getRegionDTO(region);
					
					ResponseDTO r = null;
					if(dto.getId() == 0)
						r = regionService.add(dto, user.getToken());
					else r = regionService.edit(dto, user.getToken());
					
					if(r.getError().getCode() == Constants.codeOK)
					{
						redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully saved");
						
						if(dto.isNext())
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
		 
			model.addAttribute("region", region);
			/**Writing the title, depending if is a new region of is an edition**/
			model.addAttribute("title",	setTitle(region));
		 
		 return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model ) {	
		
		Region region = new Region();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = regionService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					region = ParseJSON.getInstance().getRegionDTO(r.getResponse());
					model.addAttribute("region", region);
					/**Writing the title, depending if is a new region of is an edition**/
					model.addAttribute("title",	setTitle(region));
					
					return new ModelAndView(folder+"/add");
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = regionService.activate(state, id, user.getToken());
				if(r.getError().getCode() != Constants.codeOK)
					redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				
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
				ResponseDTO r = regionService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
					redirect.addFlashAttribute(Constants.infomsg,"Successfully deleted");
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
	
	private String setTitle(Region r){
		String title = "";
		if(r.getId() == 0)
			title = "Create New Region";
		else title = "Edit " + r.getName();
		
		return title;
	}
}
