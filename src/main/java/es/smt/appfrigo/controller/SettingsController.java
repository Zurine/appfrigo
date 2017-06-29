package es.smt.appfrigo.controller;


import java.io.IOException;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.bean.Password;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.UserAdminDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IEnterpriseService;
import es.smt.appfrigo.service.IUserService;

@Controller
@RequestMapping("/settings")
@SessionAttributes(value =  {"enterpriseList"}) 
public class SettingsController {

	private String folder = "settings";
	
	private static ImageSet settings;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IEnterpriseService enterpriseService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(Model model ) {	
		
		UserAdminDTO u = new UserAdminDTO();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			List<Default> enterprises = null;
			u.setEmail(user.getEmail());
			u.setName(user.getUsername() +" " + user.getSurname());
			u.setId(user.getToken().getUserId());
			u.setEmailNot(user.getEmailNot());
			u.setImage(new Image(user.getImage()));
			if(user.getAuthorities()!=null)
			{
				u.setTypeDesc(user.getAuthorities().get(0).getName());
				u.setTypeDesc(u.getTypeDesc().replace("ROLE_", "").toLowerCase());
				
			}
			
			try {
				ResponseDTO r = enterpriseService.listEnterprises(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
					enterprises = ParseJSON.getInstance().getDefaultDTOList(r.getResponse());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("enterpriseList", enterprises);
			
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("user", u);
		

		return new ModelAndView(folder+"/profile");
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView profile(@Valid Password user, String old, BindingResult bindingResult,ModelMap model) {	
		
		 UserAuth userAuth = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if(userAuth!=null && userAuth.isCredentialsNonExpired())
		 {
			 if(user!=null && user.getPassword()!=null && user.getRepPassword()!=null && user.getPassword().equals(user.getRepPassword()))
			 { 
				try {
					ResponseDTO r = userService.changePassword(user.getOldPassword(),user.getPassword(), userAuth.getToken());
					if(r.getError().getCode() == Constants.codeOK)
						 model.addAttribute(Constants.infomsg,"Passwords changed"); 
					else model.addAttribute(Constants.errormsg, r.getError().getDesc());
	
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 else 
			 {
				 model.addAttribute(Constants.errormsg,"Passwords don't match"); 
			 }
		}
		else return new ModelAndView(Constants.logoutRedirect);
		 
		model.addAttribute("old", "");
		model.addAttribute("user", user);
		 
		return new ModelAndView(folder+"/profile");
	 }
	
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(Model model ) {	
		
		Password p = new Password();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			
			p.setUsername(user.getUsername());
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("old", "");
		model.addAttribute("user", p);

		return new ModelAndView(folder+"/changePassword");
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@Valid Password user, String old, BindingResult bindingResult,ModelMap model) {	
		
		 UserAuth userAuth = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if(userAuth!=null && userAuth.isCredentialsNonExpired())
		 {
			 if(user!=null && user.getPassword()!=null && user.getRepPassword()!=null && user.getPassword().equals(user.getRepPassword()))
			 { 
				try {
					ResponseDTO r = userService.changePassword(user.getOldPassword(),user.getPassword(), userAuth.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						 model.addAttribute(Constants.infomsg,"Passwords changed"); 
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
				 model.addAttribute(Constants.errormsg,"Passwords don't match"); 
			 }
		}
		else return new ModelAndView(Constants.logoutRedirect);
		 
		model.addAttribute("old", "");
		model.addAttribute("user", user);
		 
		return new ModelAndView(folder+"/changePassword");
	 }
	
	
	@RequestMapping(value = {"/profile"}, params={"addFile"})
    public ModelAndView addFile(final UserAdminDTO user, final BindingResult bindingResult, ModelMap model, HttpServletRequest request){
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(user.getImage()!=null && user.getImage().getFile()!=null)
        	{ 
        		try {
					settings = ImageManager.getInstance().addImage(user.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
		     		if(settings!=null)
	        		{
		     			user.getImage().setPath(settings.getPath());
	        		}
	        		else  model.addAttribute(Constants.errormsg,"Something was wrong");
				} catch (IOException e) {
					model.addAttribute(Constants.errormsg,"Error reading the image");
					e.printStackTrace();
				}
   
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        
        model.addAttribute("user", user);
        model.addAttribute("crop", true);    
        
        return new ModelAndView(folder+"/profile");
	}
	

	
	@RequestMapping(value = {"/profile"}, params={"fileDone"})// , params={"fileDone"}
	public ModelAndView fileDone(final UserAdminDTO user,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request)  {
	 
		if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } 
		else {
        	
			UserAuth userAuth = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(userAuth!=null && userAuth.isCredentialsNonExpired())
			{
        	
				try {
					String path = ImageManager.getInstance().imageDone(settings, user.getImage(),true);
	        	
			    	if(path!=null)
			    	{
	
			    		ResponseDTO r = userService.updateImage(path, userAuth.getToken());
						if(r.getError().getCode() == Constants.codeOK){
							userAuth.setImage(path);
							
							return new ModelAndView("redirect:/"+folder+"/profile");
						}
							
						else model.addAttribute(Constants.errormsg, r.getError().getDesc());
			    	}
			    	else model.addAttribute(Constants.errormsg,"Something was wrong");
		    	
			    	model.addAttribute("crop", false);    
				} catch (IOException e) {
					model.addAttribute(Constants.errormsg,"Error reading the image");
					e.printStackTrace();
				}
			}
    		else return new ModelAndView(Constants.logoutRedirect);
	    }
	       
        model.addAttribute("user", user);
        
        return new ModelAndView(folder+"/profile");
   }
	
	@RequestMapping(value = "/support", method = RequestMethod.GET)
	public ModelAndView support(Model model ) {	
		

		return new ModelAndView(folder+"/support");
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(Boolean state, Model model,RedirectAttributes redirect){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = userService.updateSetttings(2, state, user.getToken());
				if(r.getError().getCode() != Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
				else user.setEmailNot(state);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/profile");
	 }
	
	
	@RequestMapping(value="/updateEnterprise", method = RequestMethod.GET) //, params={"getDistributor"}
	public String  updateEnterprise(int enterpriseId,ModelMap model, HttpServletRequest request) {
		
//		List<Distributor> list = new ArrayList<Distributor>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
//			try {
				user.getToken().setEnterpriseId(enterpriseId);
//				list = getDistributor(equipment,user.getToken());
				
//				if(list == null || list.size() == 0)
//				{
//					list.add(new Distributor(0, "There are not distributors"));
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		} 


		
		return folder+"/profile :: fragment-enter";
	}
	
}
