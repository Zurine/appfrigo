package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import es.smt.appfrigo.bean.Beacon;
import es.smt.appfrigo.bean.BusinessAdd;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.service.IBeaconService;
import es.smt.appfrigo.service.IBusinessService;

@Controller
@RequestMapping("/equipment/beacon")
public class EquipmentBeaconController {

	private String folder = "equipment/beacon";
	
	@Autowired
	private IBeaconService beaconService;
	
	@Autowired
	private IBusinessService businessService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("id") String id,Model model) {	
		
		List<Beacon> result = new ArrayList<Beacon>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				model.addAttribute("businessId", id);
				ResponseDTO r = businessService.getBeacons(Integer.parseInt(id), user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getBeaconList(r.getResponse());
					model.addAttribute("beaconList", result);
					
					return new ModelAndView(folder+"/list");
				}
				else
				{
					model.addAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("id") String id,Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(id!=null)
			{
				int businessId = Integer.parseInt(id);
				if(businessId!=0)
				{
					try {
						ResponseDTO r = beaconService.getList(user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							BusinessAdd business = new BusinessAdd();
							List<Beacon> beacons =  BeanManager.getInstance().getBeaconList(r.getResponse());
							
							Iterator<Beacon> i = beacons.iterator();
							Beacon b = null;
							while (i.hasNext()) {
							   b = i.next();
							   if(b.getBusiness().getId()!=0)
								   i.remove();
							}
							
							business.setList(new ArrayList<Integer>());
							business.setId(businessId);
							
							model.addAttribute("beaconList", beacons);
							model.addAttribute("business", business);
						}
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid BusinessAdd business, BindingResult bindingResult,ModelMap model,HttpServletRequest request,RedirectAttributes redirectAttributes) {	
		
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
				 if(business.getId()!=0)
				 {
					try {
						
						if(business.getList()!=null && business.getList().size()>0)
						{
							ResponseDTO r = businessService.addBeacon(business.getId(),business.getList(),user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								//model.addAttribute("business", business);
								
								return new ModelAndView("redirect:/"+folder+"/list?id="+business.getId());
							}
							else
							{
								model.addAttribute(Constants.errormsg, r.getError().getDesc());
							}
						}
						else redirectAttributes.addFlashAttribute(Constants.errormsg, "We have send you an email");
						
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
			}
			else
			{
				redirectAttributes.addFlashAttribute(Constants.errormsg, "Something was wrong"); //Login problem
			}
		 }
		 
		 model.addAttribute("business", business);
			
		 return new ModelAndView("redirect:/"+folder+"/add?id="+business.getId());
	 }
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam("id") int id,@RequestParam("business") int business,Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.removeBeacon(business, id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					return new ModelAndView("redirect:/"+folder+"/list?id="+business);
				}
				else
				{
					model.addAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		 return new ModelAndView("redirect:/"+folder+"/list?id="+business);
	 }
}
