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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.Response;
import es.smt.appfrigo.bean.TimeZone;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.constants.PropertiesManager;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.listener.ApplicationStartedListener;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.model.UserAdminDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IOperatorService;
import es.smt.appfrigo.service.IUserService;
import es.smt.appfrigo.utils.TimeZoneUtil;

@Controller
@SessionAttributes(value =  {"timezoneList"})
@RequestMapping("/operator")
public class OperatorController {

	private String folder = "operator";
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IOperatorService operatorService;
	
	@Autowired
	private IUserService userService;
	
	private static ImageSet settings;
	
	private static List<TimeZone> timezoneList;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("state") int state,Model model ) {	
		
		List<Operator> result = new ArrayList<Operator>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = operatorService.list(state,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getOperatorDTOList(r.getResponse());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("state", state);
		model.addAttribute("operatorList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id,Model model ) {	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = operatorService.get(id,true, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					Operator o = ParseJSON.getInstance().getOperatorDTO(r.getResponse());
					model.addAttribute("operator", o);
					
					return new ModelAndView(folder+"/details");
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired()){		
			if(timezoneList == null)
				timezoneList = TimeZoneUtil.getTimeZones();
			
			Operator o = new Operator();
			o.setUser(new UserAdminDTO());
			o.setZone("Europe/Madrid");
			o.setImage(new Image(Constants.defaultImage));
			/**Writing the title, depending if is a new operator of is an edition**/
			model.addAttribute("title",	setTitle(o));
			model.addAttribute("currencies",new ApplicationStartedListener().getCurrencies());
			model.addAttribute("timezoneList",timezoneList);
			model.addAttribute("operator",o);
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Operator operator, BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes) {	
		
//		 String [] split = operator.getTimezone().split(",");
//		 operator.setTimezone(split[1]);
		 if (bindingResult.hasErrors()) {
			 model.addAttribute(Constants.errormsg,
				 ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
			 
		 }
		 else{
			 UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 if(user!=null && user.isCredentialsNonExpired())
			 {
				 try {
					 ResponseDTO r = null;
					 /**Create a new operator**/
					 operator.setUrl(operator.getImage().getPath());
					 if(operator.getId() == 0){
		
						 if((operator.getUser().getEmail()==null || operator.getUser().getEmail().isEmpty()) || (operator.getUser().getPassword()!=null && !operator.getUser().getPassword().equals("") && operator.getUser().getPassword().equals(operator.getUser().getPasswordRep())))
						 {
							 if(operator.getCif()!=null && !operator.getCif().isEmpty())
								 operator.setCif(operator.getCif().trim());
							 
							 r = operatorService.add(operator, user.getToken());
							 if(r.getError().getCode() == Constants.codeOK){
								Operator o = ParseJSON.getInstance().getOperatorDTO(r.getResponse());
								session.setAttribute("step", 1);
								
								if(operator.getUser()!=null && operator.getUser().getEmail()!=null && !operator.getUser().getEmail().isEmpty()){
									Response res = addUserOperator(operator,o.getId(), user.getToken());
									 if(r.getError().getCode() == Constants.codeOK){
										 redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully Saved");
									 }
									 else redirectAttributes.addFlashAttribute(Constants.errormsg, res.getMessage());
								}
								else redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully Saved");
								
								if(operator.isNext())
									return new ModelAndView("redirect:/equipment/add?operatorId="+o.getId());
								else return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
							 }
							 else model.addAttribute(Constants.errormsg, r.getError().getDesc());
							 
						 }else {
							 model.addAttribute(Constants.errormsg,"Password does not match");
						}
					 }
					 else{
						 r = operatorService.edit(operator, user.getToken());
						 if(r.getError().getCode() == Constants.codeOK){
							 Operator o = ParseJSON.getInstance().getOperatorDTO(r.getResponse());
							 
							 if(operator.isNext())
								return new ModelAndView("redirect:/equipment/add?operatorId="+o.getId());
							else return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
						 }
						 else model.addAttribute(Constants.errormsg, r.getError().getDesc());
					 }
					 
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else return new ModelAndView(Constants.logoutRedirect);

		 }
		 /**Writing the title, depending if is a new operator of is an edition**/
		 model.addAttribute("title",	setTitle(operator));
		 model.addAttribute("currencies",	new ApplicationStartedListener().getCurrencies());
		 model.addAttribute("timezoneList",	timezoneList);
		 model.addAttribute("operator",operator);
		 
		 return new ModelAndView(folder+"/add");
	}
	
	private String setTitle(Operator o){
		String title = "";
		if(o.getId() == 0)
			title = "Create New Operator";
		else title = "Edit " + o.getName();
		
		return title;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model ) {	
		
		Operator operator = null;
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = operatorService.get(id,false, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					operator = ParseJSON.getInstance().getOperatorDTO(r.getResponse());
					
					if(timezoneList == null)
						timezoneList = TimeZoneUtil.getTimeZones();
					operator.setImage(new Image(operator.getUrl()));
					/**Writing the title, depending if is a new operator of is an edition**/
					model.addAttribute("title",	setTitle(operator));
					model.addAttribute("currencies",	new ApplicationStartedListener().getCurrencies());
					model.addAttribute("timezoneList",	timezoneList);
					model.addAttribute("operator", operator);
					
					return new ModelAndView(folder+"/add");
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
		else return new ModelAndView(Constants.logoutRedirect);

		 return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = operatorService.activate(state, id, user.getToken());
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

		if(request.getHeader("Referer").contains("list"))
			return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
	 }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = operatorService.delete( id, user.getToken());
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

//		return new ModelAndView("redirect:/"+folder+"/get?id="+id);
		if(request.getHeader("Referer").contains("list"))
			return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
	}
	
	@RequestMapping(value = {"/add"}, params={"addFile"})
    public ModelAndView addFile(final Operator operator, final BindingResult bindingResult, ModelMap model, HttpServletRequest request){
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(operator.getImage()!=null && operator.getImage().getFile()!=null)
        	{ 
        		try {
					settings = ImageManager.getInstance().addImage(operator.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
		     		if(settings!=null)
	        		{
		     			operator.getImage().setPath(settings.getPath());
	        		}
	        		else  model.addAttribute(Constants.errormsg,"Something was wrong");
				} catch (IOException e) {
					model.addAttribute(Constants.errormsg,"Error reading the image");
					e.printStackTrace();
				}
   
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        
        model.addAttribute("operator", operator);
        model.addAttribute("crop", true);    
        
    	return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = {"/add"}, params={"fileDone"})
	public ModelAndView fileDone(final Operator operator,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request)  {
	 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
			try {
				String path = ImageManager.getInstance().imageDone(settings, operator.getImage(),false);
	        	
		    	if(path!=null)
		    	{
		    		operator.getImage().setPath(path);
		    	}
		    	else model.addAttribute(Constants.errormsg,"Something was wrong");
		    	
		    	model.addAttribute("crop", false);    
			} catch (IOException e) {
				model.addAttribute(Constants.errormsg,"Error reading the image");
				e.printStackTrace();
			}
	    }
	        
        model.addAttribute("operator", operator);
        
    	return new ModelAndView(folder+"/add");
   }
	
	
	
	private Response addUserOperator(Operator operator, int operatorId, TokenDTO token) throws JsonProcessingException, IOException{
		
		Response response = new Response();

		operator.getUser().setType(Integer.parseInt(PropertiesManager.getInstance().getProperty("user.operator")));
		operator.getUser().setActive(true);
		operator.getUser().setEnterpriseId(token.getEnterpriseId());
		List<Integer> business = new ArrayList<Integer>();
		business.add(operatorId);
	
		ResponseDTO r = userService.add(operator.getUser(),business,token);
		response.setCode(r.getError().getCode());
		response.setMessage(r.getError().getDesc());

		
		return response;
	}

	
}
