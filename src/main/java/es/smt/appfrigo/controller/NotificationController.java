package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.bean.Notification;
import es.smt.appfrigo.bean.NotificationSend;
import es.smt.appfrigo.bean.OperatorF;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.INotificationService;
import es.smt.appfrigo.service.ISellerService;
import es.smt.appfrigo.utils.TitleUtil;

@Controller
@RequestMapping("/notification/seller")
public class NotificationController {

	private String folder = "notification/seller";
	
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private ISellerService sellerService;
	
	private static ImageSet settings;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Notification> result = new ArrayList<Notification>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = notificationService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getNotificationList(r.getResponse());
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
				ResponseDTO r = notificationService.get(id,true,user.getToken());
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
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model ) {	

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			Notification n = new Notification();
			n.setImage(new Image(Constants.defaultImage));
			model.addAttribute("notification",n);
			model.addAttribute("title",	TitleUtil.getInstance().setNotification(n));
			
			return new ModelAndView(folder+"/add");
		}
		else return new ModelAndView(Constants.logoutRedirect);
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Notification notification, BindingResult bindingResult,ModelMap model, RedirectAttributes redirect) {	
		
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
					ResponseDTO r = null;
					notification.setType(1);
					
					boolean result = false;
					notification.setPhoto(notification.getImage().getPath());
					if(notification.getId()==0){
						r = notificationService.add(notification, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
							result = true;
						else model.addAttribute(Constants.errormsg, r.getError().getDesc());
					}
					else{
						r = notificationService.edit(notification, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
							result = true;
						else model.addAttribute(Constants.errormsg, r.getError().getDesc());
					}
					
					if(result){
						int id = (int) r.getResponse();
						redirect.addFlashAttribute(Constants.infomsg, "Notification Successfully saved");
						if(notification.isNext()){
							 return new ModelAndView("redirect:/"+folder+"/send?id="+id);
						}
						else return new ModelAndView("redirect:/"+folder+"/list");
					}
		
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 else return new ModelAndView(Constants.logoutRedirect);
		 }
		 
		 model.addAttribute("title",	TitleUtil.getInstance().setNotification(notification));
		 
		 return new ModelAndView(folder+"/add");
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
					Notification n = ParseJSON.getInstance().getNotification(r.getResponse());
					n.setImage(new Image(n.getPhoto()));
					model.addAttribute("notification", n);
					
					model.addAttribute("title",	TitleUtil.getInstance().setNotification(n));
					
					return new ModelAndView(folder+"/add");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("notification", new Notification());
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }

	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView send(@RequestParam("id") int id,Model model, RedirectAttributes redirect) {	
		
		NotificationSend ns = new NotificationSend();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				
				ResponseDTO r = sellerService.listByOperators( user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<OperatorF> users = ParseJSON.getInstance().getOperatorFList(r.getResponse());

					ns.setId(id);
					ns.setUsers(new ArrayList<Integer>());
					model.addAttribute("operators", users);
					model.addAttribute("notification", ns);

					return new ModelAndView(folder+"/send");
				}
				else redirect.addFlashAttribute(Constants.errormsg,r.getError().getDesc());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView send(@Valid NotificationSend notification, BindingResult bindingResult,ModelMap model, RedirectAttributes redirect) {	
		
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
				if(notification!=null && notification.getId()!=0)
				{
					try {
						
						ResponseDTO r = notificationService.send(notification.getId(), notification.getUsers(), null,1, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							redirect.addFlashAttribute(Constants.infomsg, "Notification Successfully sended");
							
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
			else return new ModelAndView(Constants.logoutRedirect);
		 }
		 
		 return new ModelAndView("redirect:/"+folder+"/edit");
	 }
	
	
	@RequestMapping(value = {"/add"}, params={"addFile"})
    public ModelAndView addFile(final Notification notifcation, final BindingResult bindingResult, ModelMap model, HttpServletRequest request){
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(notifcation.getImage()!=null && notifcation.getImage().getFile()!=null)
        	{ 
        		try {
					settings = ImageManager.getInstance().addImage(notifcation.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
		     		if(settings!=null)
	        		{
		     			notifcation.getImage().setPath(settings.getPath());
	        		}
	        		else  model.addAttribute(Constants.errormsg,"Something was wrong");
				} catch (IOException e) {
					model.addAttribute(Constants.errormsg,"Error reading the image");
					e.printStackTrace();
				}
   
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        model.addAttribute("title",	TitleUtil.getInstance().setNotification(notifcation));
        model.addAttribute("notifcation", notifcation);
        model.addAttribute("crop", true);    
        
    	return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = {"/add"}, params={"fileDone"})
	public ModelAndView fileDone(final Notification notifcation,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request)  {
	 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
			try {
				String path = ImageManager.getInstance().imageDone(settings, notifcation.getImage(),false);
	        	
		    	if(path!=null)
		    	{
		    		notifcation.getImage().setPath(path);
		    	}
		    	else model.addAttribute(Constants.errormsg,"Something was wrong");
		    	
		    	model.addAttribute("crop", false);    
			} catch (IOException e) {
				model.addAttribute(Constants.errormsg,"Error reading the image");
				e.printStackTrace();
			}
	    }
	        
        model.addAttribute("notifcation", notifcation);
        model.addAttribute("title",	TitleUtil.getInstance().setNotification(notifcation));
        
    	return new ModelAndView(folder+"/add");
   }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = notificationService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
					redirect.addFlashAttribute(Constants.infomsg,"Successfully deleted");
				else redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

//		return new ModelAndView("redirect:/"+folder+"/get?id="+id);
		if(request.getRequestURI().contains("list"))
			return new ModelAndView("redirect:/"+folder+"/list");
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
	}
}
