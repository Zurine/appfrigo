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
import org.springframework.web.bind.annotation.SessionAttributes;
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
import es.smt.appfrigo.bean.ProductMicro;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.CompositionDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.ICategoryService;
import es.smt.appfrigo.service.IProductService;
import es.smt.appfrigo.utils.TitleUtil;

@Controller
@RequestMapping("/product")
@SessionAttributes(value =  {"categoryList"})
public class ProductController  implements HandlerExceptionResolver{

	private String folder = "product";
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	private static ImageSet settings;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("state") int state,Model model ) {	
		
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = productService.list(state,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result =  ParseJSON.getInstance().getProductDTOList(r.getResponse());
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
		model.addAttribute("productList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id,Model model ,RedirectAttributes redirectAttributes) {	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = productService.get(id, true, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("product", ParseJSON.getInstance().getProductDTO(r.getResponse()));
					
					return new ModelAndView(folder+"/details");
				}
				else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView("redirect:/"+folder+"/list?state=1");
	}
	
	@RequestMapping(value={"/addCategory"}) 
	public String addCategory(final ProductDTO product, final BindingResult bindingResult, HttpServletRequest request,Model model ) {	
		if(product.getType()==1)
		{
			CompositionDTO c = new CompositionDTO();
			c.setCategory(new Category());
			c.setAmount(1);
			c.setQuantity(1);
			product.getComposition().add(c);
		}
		
		model.addAttribute("product",product);
		
		return folder+"/add :: fragment-category";
	}	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			ProductDTO p =  new ProductDTO();
			p.setActive(true);
			p.setCategory(new Category());
			p.setImage(new Image(Constants.defaultImage));
			List<CompositionDTO> compositions = new ArrayList<CompositionDTO>();
			CompositionDTO c = new CompositionDTO();
			c.setCategory(new Category());
			c.setAmount(1);
			c.setQuantity(1);
			compositions.add(c);
			p.setComposition(compositions);
			model.addAttribute("product",p);
			
			model.addAttribute("title",TitleUtil.getInstance().setProduct(p));
			
			try {
				ResponseDTO r = productService.listMini(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("crop", false);    
					
					r = productService.listCategories(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Category> cat = new ArrayList<Category>();
						cat.add(new Category(-1, "New Category Same Name"));
						cat.addAll(ParseJSON.getInstance().getCategoryDTOList(r.getResponse()));		
						model.addAttribute("categoryList", cat);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid ProductDTO product, BindingResult bindingResult,ModelMap model, RedirectAttributes redirectAttributes) {	
		
		if (bindingResult.hasErrors()) {
			 model.addAttribute(Constants.errormsg,
				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 			 
		}
		else{
			UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if(user!=null && user.isCredentialsNonExpired()){
			 
				boolean error = false;
				if(product.getType() == 0 && product.getCategory()!=null) 
					product.setComposition(new ArrayList<CompositionDTO>()); 
				else {
					for(CompositionDTO pm:product.getComposition())
					{
						 if(pm.getCategory()!=null)
						 {
							 if(pm.getCategory().getId()!=0 && pm.getAmount()==0)
							 {
								 error = true;
								 break;
							 }
						 }
					}
				}
				if(!error){
				
					try {
						
						ResponseDTO r = null;
						product.setEnterpriseId(user.getToken().getEnterpriseId()); 
						product.setUrl(product.getImage().getPath());
						
						/**New Category**/
						if(product.getCategory().getId() == -1){
							product.getCategory().setName(product.getName());
							product.getCategory().setUrl(product.getUrl());
							product.getCategory().setActive(product.isActive());
							r = categoryService.add(product.getCategory(), user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
								product.setCategory(ParseJSON.getInstance().getCategoryDTO(r.getResponse()));
						}
						
						if(product.getId() == 0){
						
							r = productService.add(product, user.getToken());
							if(r.getError().getCode() == Constants.codeOK){
								/**Save to show the assistant**/
								session.setAttribute("step", 4);
								
								if(product.isNext())
									return new ModelAndView("redirect:/"+folder+"/add");
								else return new ModelAndView("redirect:/"+folder+"/list?state=1");
							}
							else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						}
						else{
							r = productService.edit(product, user.getToken());
							if(r.getError().getCode() == Constants.codeOK){
								
								if(r.getError().getDesc()!=null)
									redirectAttributes.addFlashAttribute(Constants.infomsg,"Successfully saved. " + r.getError().getDesc());
								else redirectAttributes.addFlashAttribute(Constants.infomsg,"Product saved successfully");
								
								if(product.isNext())
									return new ModelAndView("redirect:/"+folder+"/add");
								else return new ModelAndView("redirect:/"+folder+"/list?state=1");
							}
							else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						}
						
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else model.addAttribute(Constants.errormsg,"The amount of the product must be unless 1");
			}
			else return new ModelAndView(Constants.logoutRedirect);
		}
		model.addAttribute("title",TitleUtil.getInstance().setProduct(product));
		
		model.addAttribute("product",product);
		
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = productService.activate(state, id, user.getToken());
				if(r.getError().getCode() != Constants.codeOK)
					redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

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
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			ProductDTO p = new ProductDTO();
			try {
				ResponseDTO r = productService.get(id, false, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					p = ParseJSON.getInstance().getProductDTO(r.getResponse());
					
					r = productService.listCategories(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Category> cat = new ArrayList<Category>();
						cat.add(new Category(-1, "New Category Same Name"));
						cat.addAll(ParseJSON.getInstance().getCategoryDTOList(r.getResponse()));		
						model.addAttribute("categoryList", cat);
					}
				
					if(p.getComposition() == null || p.getComposition().size() == 0)
					{
						p.setComposition(new ArrayList<CompositionDTO>());
						CompositionDTO mini = new CompositionDTO();
						mini.setCategory(new Category(0, ""));
						mini.setAmount(1);
						mini.setQuantity(1);
						mini.setProductMinis(new ArrayList<ProductMicro>() );
						p.getComposition().add(mini);
					}
					else p.setType(1);
					Image i = new Image(p.getUrl());
					p.setImage(i);	
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			model.addAttribute("idRemove", 0);
			model.addAttribute("product", p);
			model.addAttribute("title",TitleUtil.getInstance().setProduct(p));
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = {"/add", "/edit"}, params={"addFile"})
    public ModelAndView addFile(final ProductDTO product, final BindingResult bindingResult, ModelMap model, HttpServletRequest request)  {
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(product.getImage()!=null && product.getImage().getFile()!=null)
        	{
        		try {
					settings = ImageManager.getInstance().addImage(product.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
		     		if(settings!=null)
		     			product.getImage().setPath(settings.getPath());
	        		else  model.addAttribute(Constants.errormsg,"Something was wrong");
				} catch (IOException e) {
					model.addAttribute(Constants.errormsg,"Error reading the image");
					e.printStackTrace();
				}
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        
        model.addAttribute("product", product);
        model.addAttribute("crop", true);    
        model.addAttribute("title",TitleUtil.getInstance().setProduct(product));
        
    	return new ModelAndView("product/add");
	}
	
	@RequestMapping(value = {"/add", "/edit"}, params={"fileDone"})
	public ModelAndView fileDone(final ProductDTO product,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request) {
	 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
	        	
			try {
				String path = ImageManager.getInstance().imageDone(settings, product.getImage(),false);
		    	if(path!=null)
		    		product.getImage().setPath(path);
		    	else model.addAttribute(Constants.errormsg,"Something was wrong");
		    	model.addAttribute("crop", false);    
		    	model.addAttribute("product", product);
		    	model.addAttribute("title",TitleUtil.getInstance().setProduct(product));
	    	
			} catch (IOException e) {
				model.addAttribute(Constants.errormsg,"Error reading the image");
				e.printStackTrace();
			}
	    }
	        
    	return new ModelAndView("product/add");
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String  delete(@RequestParam("id") int id, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = productService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
					model.addAttribute("delete", true);    
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
				ResponseDTO r = productService.deleteCascade(id, user.getToken());
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

		if(request.getHeader("Referer").contains("list"))
    		return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
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
