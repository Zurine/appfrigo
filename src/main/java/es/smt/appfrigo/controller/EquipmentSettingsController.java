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
@RequestMapping("/equipment/settings")
public class EquipmentSettingsController {
	
	private String folder = "equipment/settings";
	
	@Autowired
	private ISettingsService settingsService;

	
	@RequestMapping(value = "/getInactiveTime", method = RequestMethod.GET)
	public ModelAndView getInactiveTime(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
			
				ResponseDTO r = settingsService.getInativeTime(user.getToken());
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
			 model.addAttribute(Constants.errormsg, "Something was wrong");
		}
		
		return new ModelAndView(folder+"/inactiveTime");
		
	}
	
	@RequestMapping(value = "/setInactiveTime", method = RequestMethod.POST)
	public ModelAndView setInactiveTime(int time, ModelMap model) {	
		
		/* if (bindingResult.hasErrors()) 
		 {
			 model.addAttribute(Constants.errormsg,ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
		 }
		 else*/
		 {
			 UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(user!=null && user.isCredentialsNonExpired())
			 {
			
				try {
					ResponseDTO r = settingsService.setInativeTime(time, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						return new ModelAndView("redirect:/"+folder+"/getInactiveTime");
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
		 }
		 
		  model.addAttribute("errorMessage", "Something was wrong");
		 
		 return new ModelAndView("redirect:/"+folder+"/getInactiveTime");
	 }
}
