package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController implements HandlerExceptionResolver{


	private String folder = "category";
	
	@Autowired
	private ICategoryService categoryService;
	
	private static ImageSet settings;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Category> result = new ArrayList<Category>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = categoryService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getCategoryList(r.getResponse());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("message", "");
		model.addAttribute("categoryList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			Category c = new Category();
			c.setActive(true);
			c.setImage(new Image(Constants.defaultImage));
			model.addAttribute("category", c);
			model.addAttribute("title", setTitle(c));
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Category category, BindingResult bindingResult,ModelMap model, RedirectAttributes redirect) {	
		
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
					category.setUrl(category.getImage().getPath());
					/**Edit or save**/
					if(category.getId() == 0)
						r = categoryService.add(category, user.getToken());
					else r = categoryService.edit(category, user.getToken());
					
					/**Redirect and manange message**/
					if(r.getError().getCode() == Constants.codeOK)
					{
						redirect.addFlashAttribute(Constants.infomsg, "Successufully Saved");
						if(category.isNext())
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
		model.addAttribute("category", category);
		model.addAttribute("title", setTitle(category));
		 
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model, RedirectAttributes redirect) {	
		
		Category category = new Category();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = categoryService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					category = ParseJSON.getInstance().getCategoryDTO(r.getResponse());
					category.setImage(new Image(category.getUrl()));
					model.addAttribute("category", category);
					model.addAttribute("title", setTitle(category));
					
					return new ModelAndView(folder+"/add");
				}
				else redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

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
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model, RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = categoryService.activate(state, id, user.getToken());
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
	
	@RequestMapping(value = {"/add", "/edit"}, params={"addFile"})
    public ModelAndView addFile(final Category category, final BindingResult bindingResult, ModelMap model, HttpServletRequest request){
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(category.getImage()!=null && category.getImage().getFile()!=null)
        	{ 
        		try {
					settings = ImageManager.getInstance().addImage(category.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
		     		if(settings!=null)
	        		{
	        			category.getImage().setPath(settings.getPath());
//	        			category.getImage().setPath("http://www.nourish-mindbodysoul.co.uk/wp-content/themes/Nourish/Furniture/Green-Smoothy.jpg");
	        		}
	        		else model.addAttribute(Constants.errormsg,"Something was wrong");
				} catch (IOException e) {
					model.addAttribute(Constants.errormsg,"Error reading the image");
					e.printStackTrace();
				}
        	}
        	else model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        model.addAttribute("title", setTitle(category));
        model.addAttribute("category", category);
        model.addAttribute("crop", true);    
        
    	return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = {"/add", "/edit"}, params={"fileDone"})
	public ModelAndView fileDone(final Category category,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request)  {
	 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
			try {
				String path = ImageManager.getInstance().imageDone(settings, category.getImage(),false);
	        	
		    	if(path!=null)
		    	{
		    		category.getImage().setPath(path);
		    	}
		    	else model.addAttribute(Constants.errormsg,"Something was wrong");
		    	
		    	model.addAttribute("crop", false);    
			} catch (IOException e) {
				model.addAttribute(Constants.errormsg,"Error reading the image");
				e.printStackTrace();
			}
	    }
	        
        model.addAttribute("title", setTitle(category));
        model.addAttribute("category", category);
        
    	return new ModelAndView(folder+"/add");
   }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String  delete(@RequestParam("id") int id, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = categoryService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("delete", true);    
				}
				else {
					@SuppressWarnings("unchecked")
					List<String> message = (List<String>)r.getResponse();
					model.addAttribute("message", message); 
					if(message == null || message.size() == 0)
						model.addAttribute("delete", false);  
					else model.addAttribute("delete", true);    
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		 return folder+"/list :: fragment-message";
	}
	
	@RequestMapping(value = "/deleteCascade", method = RequestMethod.GET)
	public ModelAndView deleteCascade(@RequestParam("id") int id, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = categoryService.deleteCascade(id, user.getToken());
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

		return new ModelAndView("redirect:/"+folder+"/list");
	}
	
	private String setTitle(Category c){
		String title = "";
		if(c.getId() == 0)
			title = "Create New Category";
		else title = "Edit " + c.getName();
		
		return title;
	}
	
    /*** Trap Exceptions during the upload and show errors back in view form ***/
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
    {        
        Map<String, Object> model = new HashMap<String, Object>();
        FlashMap redirectAttributes = RequestContextUtils.getOutputFlashMap(request);
        if (exception instanceof MaxUploadSizeExceededException)
        {
        	redirectAttributes.put(Constants.errormsg,"File is too large");
        } 
        else redirectAttributes.put(Constants.errormsg, "Unexpected error: " + exception.getMessage());
        
        String redirect = request.getRequestURI().replaceFirst(request.getContextPath()+"/", "");

        return new ModelAndView("redirect:/"+redirect, model);
    }
	
}
