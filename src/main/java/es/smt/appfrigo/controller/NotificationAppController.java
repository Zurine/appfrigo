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

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Notification;
import es.smt.appfrigo.bean.NotificationSend;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.PromotionConverter;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.INotificationService;
import es.smt.appfrigo.service.IPromotionService;
import es.smt.appfrigo.service.IUserAppService;
import es.smt.appfrigo.utils.ConditionUtil;

@Controller
@RequestMapping("/notification/app")
public class NotificationAppController {

	private String folder = "notification/app";
	
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IUserAppService userAppService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Notification> result = new ArrayList<Notification>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = notificationService.listApp(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result =  ParseJSON.getInstance().getNotificationList(r.getResponse());
					for(Notification n : result){
						n.setPromotion(PromotionConverter.getInstance().welcomeToBean(n.getOffer()));
					}
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("notificationList", result);
		
		return new ModelAndView(folder+"/list");
	 }
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("id") int id,Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = notificationService.get(id,false,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					Notification n = ParseJSON.getInstance().getNotification(r.getResponse());
					model.addAttribute("notification", n);
					
					return new ModelAndView(folder+"/details");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("notification", new Notification());
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	 }
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = promotionService.getList(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("promotions", BeanManager.getInstance().getPromotionList(r.getResponse()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("notification", new Notification());
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Notification notification, BindingResult bindingResult,ModelMap model) {	
		
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
//				NotificationDTO dto = NotificationConverter.getInstance().beanToDto(notification);
				if(notification.getOffer()!=null && notification.getOffer().getOfferId()!=0)
					notification.setContent(1);
				else notification.setContent(0);
				
				try {
					notification.setType(0);
					ResponseDTO r = notificationService.add(notification, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						return new ModelAndView("redirect:/"+folder+"/list");
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
		 
		 
		 return new ModelAndView("redirect:/"+folder+"/add");
	 }
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id,Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				
				ResponseDTO r = notificationService.get(id, false,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("notification", ParseJSON.getInstance().getNotification(r.getResponse()));
					r = promotionService.getList(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						model.addAttribute("promotions", BeanManager.getInstance().getPromotionList(r.getResponse()));
					}
					
					return new ModelAndView(folder+"/edit");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("notification", new Notification());
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Notification notification, BindingResult bindingResult,ModelMap model) {	
		
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
//				 Notification dto = ParseJSON.getInstance().getNotification(notification);
			
				try {
					ResponseDTO r = notificationService.edit(notification, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						return new ModelAndView("redirect:/"+folder+"/list");
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
		 
		 
		 return new ModelAndView("redirect:/"+folder+"/edit");
	 }
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView send(@RequestParam("id") int id,Model model ) {	
		
		NotificationSend ns = new NotificationSend();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				
				ResponseDTO r = notificationService.get(id, false,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					Notification notification = ParseJSON.getInstance().getNotification(r.getResponse());
					
					ns =  ConditionUtil.getInstance().getConditions(promotionService, user.getToken(),ns);
					if(ns!=null)
					{
						ns.setNotification(notification);
						ns.setUsers(new ArrayList<Integer>());
						//model.addAttribute("conditions", c);
						//model.addAttribute("condSelected", new ArrayList<Condition>());
						model.addAttribute("notification", ns);
						
						r = userAppService.getUserDeviceList(user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							model.addAttribute("users", BeanManager.getInstance().getUserMiniList(r.getResponse()));
							model.addAttribute("ids",new ArrayList<Integer>());
							
						}
						
						return new ModelAndView(folder+"/send");
					}
					else model.addAttribute(Constants.errormsg, "Something was wrong");
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("notification", ns);
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView send(@Valid NotificationSend notification, BindingResult bindingResult,ModelMap model) {	
		
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
				if(notification!=null && notification.getNotification()!=null)
				{
					try {
					//	if(notification.getUsers() == null)
							//notification.setUsers(new ArrayList<Integer>());
						
						ResponseDTO r = notificationService.send(notification.getNotification().getId(), notification.getUsers(), notification.getSelectedConditions(),0, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							return new ModelAndView("redirect:/"+folder+"/list");
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

			}
			else
			{
				model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
			}
		 }
		 
		 
		 return new ModelAndView("redirect:/"+folder+"/edit");
	 }
}
