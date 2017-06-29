package es.smt.appfrigo.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.service.ISettingsService;

@Controller
@RequestMapping("/app/settings")
public class AppSettingsController {

	private String folder = "app/settings";
	
	@Autowired
	private ISettingsService settingsService;

		
	@RequestMapping(value = "/getOfferTime", method = RequestMethod.GET)
	public ModelAndView getOfferTime(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
			
				ResponseDTO r = settingsService.getOfferTime(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("time", r.getResponse());
				}
				else model.addAttribute("time", 0);
				 
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}
		
		return new ModelAndView(folder+"/offerTime");
		
	}
	
	@RequestMapping(value = "/setOfferTime", method = RequestMethod.POST)
	public ModelAndView setOfferTime(int time, ModelMap model) {	
		
		 UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if(user!=null && user.isCredentialsNonExpired())
		 {
		
			try {
				ResponseDTO r = settingsService.setOfferTime(time, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					return new ModelAndView("redirect:/"+folder+"/getOfferTime");
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
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}
	 
		model.addAttribute(Constants.errormsg, "Something was wrong");
		 
		return new ModelAndView("redirect:/"+folder+"/offerTime");
	 }
}
