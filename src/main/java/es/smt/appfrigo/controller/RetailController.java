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

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.bean.Retail;
import es.smt.appfrigo.bean.RetailLocation;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.RetailConverter;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.RetailDTO;
import es.smt.appfrigo.model.RetailLocationDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.service.IRetailService;

@Controller
@RequestMapping("/retail")
public class RetailController {
private String folder = "retail";
	
	@Autowired
	private IRetailService retailService;
	
	private static ImageSet settings;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Retail> result = new ArrayList<Retail>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = retailService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getRetailList(r.getResponse());
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
		model.addAttribute("retailList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id,Model model ) {	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = retailService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("retail", BeanManager.getInstance().getRetail(r.getResponse()));
					
					return new ModelAndView(folder+"/details");
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
			model.addAttribute(Constants.errormsg,"Something was wrong"); //Login problem
		}
		return new ModelAndView("redirect:/"+folder+"/list");
	}
	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			Retail r = new Retail();
			r.setImage(new Image(Constants.defaultImage));
			
			
			model.addAttribute("retail", r);
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Retail retail, BindingResult bindingResult,ModelMap model) {	
		
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
				RetailDTO dto = RetailConverter.getInstance().beanToDto(retail);
			
				try {
					ResponseDTO r = retailService.add(dto, user.getToken());
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
		 
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model) {	
		
		Retail retail = new Retail();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = retailService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					retail = BeanManager.getInstance().getRetail(r.getResponse());
					model.addAttribute("retail", retail);
					
					return new ModelAndView(folder+"/edit");
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

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Retail retail, BindingResult bindingResult,ModelMap model) {	
		
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
				 RetailDTO dto = RetailConverter.getInstance().beanToDto(retail);
			
				try {
					ResponseDTO r = retailService.edit(dto, user.getToken());
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
		 
		 return new ModelAndView(folder+"/edit");
	 }
	
	@RequestMapping(value = {"/add", "/edit"}, params={"addFile"})
    public ModelAndView addFile(final Retail retail, final BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws IOException {
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(retail.getImage()!=null && retail.getImage().getFile()!=null)
        	{ 
        		settings = ImageManager.getInstance().addImage(retail.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
        		if(settings!=null)
        		{
        			retail.getImage().setPath(settings.getPath());
        		}
        		else  model.addAttribute(Constants.errormsg,"Something was wrong");
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        
        model.addAttribute("retail", retail);
        model.addAttribute("crop", true);    
        
        if(request.getRequestURI().contains("add"))
        	return new ModelAndView(folder+"/add");
        else return new ModelAndView(folder+"/edit");
	}

	
	@RequestMapping(value = {"/add", "/edit"}, params={"fileDone"})
	public ModelAndView fileDone(final Retail retail,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws IOException {
	 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	String path = ImageManager.getInstance().imageDone(settings, retail.getImage(),false);
	        	
	    	if(path!=null)
	    	{
	    		retail.getImage().setPath(path);
	    	}
	    	else model.addAttribute(Constants.errormsg,"Something was wrong");
	    	
	    	model.addAttribute("crop", false);        	
	    }
	        
        if(request.getRequestURI().contains("add"))
        	return new ModelAndView(folder+"/add");
        else return new ModelAndView(folder+"/edit");
   }
	
	@RequestMapping(value = "location/add", method = RequestMethod.GET)
	public ModelAndView addLocation (@RequestParam("id") int id, Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			RetailLocation retail = new RetailLocation();
			retail.setRetail(new Retail(id));
			
			model.addAttribute("retail", retail);
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/location/add");
	}
	
	@RequestMapping(value = "/location/add", method = RequestMethod.POST)
	public ModelAndView addLocation(@Valid RetailLocation retail, BindingResult bindingResult,ModelMap model) {	
		
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
				RetailLocationDTO dto = RetailConverter.getInstance().beanToDto(retail);
			
				try {
					ResponseDTO r = retailService.addLocation(dto, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						return new ModelAndView("redirect:/"+folder+"/get?id="+retail.getRetail().getId());
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
		 
		return new ModelAndView(folder+"/location/add");
	}

	@RequestMapping(value = "/location/edit", method = RequestMethod.GET)
	public ModelAndView editLocation(@RequestParam("id") int id, Model model) {	
		
		RetailLocation retail = new RetailLocation();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = retailService.getLocation(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					retail = BeanManager.getInstance().getRetailLocation(r.getResponse());
					model.addAttribute("retail", retail);
					
					return new ModelAndView(folder+"/location/edit");
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

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/location/edit", method = RequestMethod.POST)
	public ModelAndView editLocation(@Valid RetailLocation retail, BindingResult bindingResult,ModelMap model) {	
		
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
				 RetailLocationDTO dto = RetailConverter.getInstance().beanToDto(retail);
			
				try {
					ResponseDTO r = retailService.editLocation(dto, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						return new ModelAndView("redirect:/"+folder+"/get?id="+retail.getRetail().getId());
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
		 
		 return new ModelAndView(folder+"/location/edit");
	 }
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,@RequestParam("retail") int retail,Boolean state, Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = retailService.activate(state, id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					return new ModelAndView("redirect:/"+folder+"/get?id="+retail);
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

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	
}
