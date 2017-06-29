package es.smt.appfrigo.controller;

import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.NotificationSend;
import es.smt.appfrigo.bean.PromoProduct;
import es.smt.appfrigo.bean.Promotion;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.blob.BlobManager;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.PromotionConverter;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.CompositionDTO;
import es.smt.appfrigo.model.OfferDTO;
import es.smt.appfrigo.model.ProductCompositionDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IProductService;
import es.smt.appfrigo.service.IPromotionService;
import es.smt.appfrigo.utils.ConditionUtil;

@Controller
@RequestMapping("/promotion")
@SessionAttributes(value =  {"categories","listAge", "listGender","composition"}) // {"categories",  "compound",  "composition"}
public class PromotionControler {

	private String folder = "promotion";
	
	private static String location = "";
	private static String imageName = "";
	private static String imageExtension = "";
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private IPromotionService promotionService;

	@Autowired
	private IProductService productService;

	
    @InitBinder(value="products")
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(PromoProduct.class, new PartPropertyEditor ());
    }

    private static class PartPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String partId) {
            final PromoProduct part = new PromoProduct(Integer.parseInt(partId)); // Get part based on the id 
            setValue(part);
        }

        /**
         * This is called when checking if an option is selected
         */
        @Override
        public String getAsText() {
           return ((ProductDTO)getValue()).getId()+""; // don't forget null checking
        }
    }
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model ) {	
		
		List<Promotion> result = new ArrayList<Promotion>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = promotionService.getList(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getPromotionList(r.getResponse());
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
		model.addAttribute("promotionList", result);
		
		return new ModelAndView(folder+"/list");
	 }
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id, Model model ) {	
		
		Promotion promotion = new Promotion();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = promotionService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					promotion = BeanManager.getInstance().getPromotion(r.getResponse());
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
		model.addAttribute("promotion", promotion);
		
		
		return new ModelAndView(folder+"/details");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = productService.listByCategories(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<Category> products = ParseJSON.getInstance().getCategoryDTOList(r.getResponse());
					model.addAttribute("categories", products);
					//String compound =   new ObjectMapper().writer().writeValueAsString(products);
					model.addAttribute("compound", new ObjectMapper().writer().writeValueAsString(products));
					
					
					NotificationSend ns = new NotificationSend();
					ns =  ConditionUtil.getInstance().getConditions(promotionService, user.getToken(),ns);
					model.addAttribute("listAge", ns.getListAge());
					model.addAttribute("listTemp", ns.getListTemperature());
					model.addAttribute("listGender", ns.getListGender());
					model.addAttribute("composition", new ArrayList<CompositionDTO>());
					
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Promotion p = new Promotion();
			p.setProducts(new ArrayList<PromoProduct>());
			p.setImage(new Image());
			model.addAttribute("crop", false);    
			
			model.addAttribute("typeList", ConditionUtil.getInstance().getPromotionType());
			List<PromoProduct> pp = new ArrayList<PromoProduct>();
			pp.add(new PromoProduct());
			p.setProducts(pp);
			model.addAttribute("products", pp);
			model.addAttribute("promotion", p);
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Promotion promotion, BindingResult bindingResult,ModelMap model) {	
		
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
					
				
					
				 OfferDTO dto = PromotionConverter.getInstance().beanToDto(promotion);
				 if(promotion.getProductIds()!=null && promotion.getProductIds().size() > 0)
				 {
					 dto.setProducts(new ArrayList<ProductCompositionDTO>());
					 for (Integer i : promotion.getProductIds()) {
						dto.getProducts().add(new ProductCompositionDTO(i));
					}
				 }
				 dto.setEnterpriseId(user.getToken().getEnterpriseId());
				try {
					ResponseDTO r = promotionService.add(dto, user.getToken());
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
		 
		 model.addAttribute("errorMessage", "Something was wrong");
		 
		 return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value={"/add"}, params={"getComposed"})
	public ModelAndView getComposed(final Promotion promotion, final BindingResult bindingResult, HttpServletRequest request,ModelMap model) {
		
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	

			if(promotion!=null && promotion.getSelected()!=null && !promotion.getSelected().equals(""))
			{
				String [] prod = promotion.getSelected().split("@@");
				
				if(prod!=null &&prod.length>1 )
				{
					@SuppressWarnings("unchecked")
					List<Category> products = (List<Category>)session.getAttribute("categories");
					
					try {
						model.addAttribute("compound", new ObjectMapper().writer().writeValueAsString(products));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					
					@SuppressWarnings("unchecked")
					List<CompositionDTO> composition = (List<CompositionDTO>)session.getAttribute("composition");
					
					if(composition == null)
						composition = new ArrayList<CompositionDTO>();

					if(products!=null && products.size()>0)
					{
						boolean found = false;
						int cont = 0;
						int contProd = 0;
						while(!found && cont < products.size())
						{
							if(products.get(cont).getId() == Integer.parseInt(prod[0]))
							{
								while(!found && contProd< products.get(cont).getProducts().size())
								{
									if(products.get(cont).getProducts().get(contProd).getId() == Integer.parseInt(prod[1]))
									{
										composition.addAll(products.get(cont).getProducts().get(contProd).getComposition());
										model.addAttribute("composition", composition);
										found = true;
									}
								}
							}
							cont++;
						}
					}
				}
			}
		}
		
		model.addAttribute("promotion", promotion);
		
		return new ModelAndView(folder+"/add");
	}	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model ) {	
		
		Promotion promotion = new Promotion();
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = promotionService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					promotion = BeanManager.getInstance().getPromotion(r.getResponse());
					model.addAttribute("promotion", promotion);
					r = productService.list(1,user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						products = ParseJSON.getInstance().getProductDTOList(r.getResponse());
						
					}
					model.addAttribute("products", products);
					NotificationSend ns = new NotificationSend();
					ns =  ConditionUtil.getInstance().getConditions(promotionService, user.getToken(),ns);
					model.addAttribute("listAge", ns.getListAge());
					model.addAttribute("listTemp", ns.getListTemperature());
					model.addAttribute("listGender", ns.getListGender());
					model.addAttribute("typeList", ConditionUtil.getInstance().getPromotionType());
					
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
	public ModelAndView edit(@Valid Promotion promotion, BindingResult bindingResult,ModelMap model) {	
		
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
				 OfferDTO dto = PromotionConverter.getInstance().beanToDto(promotion);
			
				try {
					ResponseDTO r = promotionService.edit(dto, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						return new ModelAndView("redirect:/"+"/beacon/list");
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
		 
		 return new ModelAndView("redirect:/"+folder+"/add");
	 }
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = promotionService.activate(id, user.getToken());
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

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/desactivado", method = RequestMethod.GET)
	public ModelAndView desactivado(@RequestParam("id") int id,Boolean state, Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = promotionService.desactivate(id, user.getToken());
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

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/listPromotions", method = RequestMethod.GET)
	public ModelAndView listPromotions(@RequestParam("id") int id,Model model ) {	
		
		List<Promotion> result = new ArrayList<Promotion>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = promotionService.getList(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getPromotionList(r.getResponse());
					
					System.out.println("Length-> " + result.size());
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
		model.addAttribute("promotionList", result);
		
		return new ModelAndView(folder+"/list");
	 }
	
	@RequestMapping(value = "/setWelcome", method = RequestMethod.GET)
	public ModelAndView setWelcome(@RequestParam("id") int id,Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = promotionService.setWelcomeOffer(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute(Constants.errormsg,r.getError().getDesc()); 
				}
				else model.addAttribute(Constants.errormsg,r.getError().getDesc()); 
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Promotion p = new Promotion();
		//	p.setProductsId(new ArrayList<Integer>());
		
			model.addAttribute("typeList", ConditionUtil.getInstance().getPromotionType());
			model.addAttribute("promotion", p);
		}
		else
		{
			model.addAttribute(Constants.errormsg, "Something was wrong"); 
		}

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	//@RequestMapping(value = {"/add", "/edit"}, params={"addFile"})
	@RequestMapping(value = "/addFile", method = RequestMethod.POST)
	
    public String addFile(final Promotion promotion, ModelMap model, HttpServletRequest request) throws IOException {
	 
		location = session.getServletContext().getRealPath("/");
		 
     /*   if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {*/
        	
        	if(promotion.getImage()!=null && promotion.getImage().getFile()!=null)
        	{// 
        		imageExtension = FilenameUtils.getExtension(promotion.getImage().getFile().getOriginalFilename());
        		imageName = UUID.randomUUID().toString()+ "." + imageExtension;
	   		    location = session.getServletContext().getRealPath("/") + "resources/out/" + imageName;
	            
	   		    // Now do something with file...
	            FileCopyUtils.copy(promotion.getImage().getFile().getBytes(), new File(location));
	            promotion.getImage().setPath(session.getServletContext().getContextPath()+"/container/" +imageName);
	           
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
	            
      //  }
        	 
        model.addAttribute("imageUrl", promotion.getImage().getPath());
        model.addAttribute("promotion", promotion);
        model.addAttribute("crop", true);    
        
        if(request.getRequestURI().contains("add"))
        	return "promotion/add :: resultsList";
        else return "promotion/edit";
   //     return promotion.getImage().getPath().toString();
	}

	
	//@RequestMapping(value = {"/add", "/edit"}, params={"fileDone"})
	@RequestMapping(value = "/fileDone", method = RequestMethod.POST)
	@ResponseBody
	public String fileDone(final Promotion promotion, ModelMap model, HttpServletRequest request) throws IOException {
	 
     //   if (bindingResult.hasErrors()) {
     //   	model.addAttribute(Constants.errormsg,
   // 				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
   //     } else {
	        	
        	BufferedImage image = ImageIO.read(new File(location));
	        	
        	BufferedImage out = image.getSubimage( ((Double)promotion.getImage().getX()).intValue(), ((Double)promotion.getImage().getY()).intValue(),
        			((Double)promotion.getImage().getW()).intValue(), ((Double)promotion.getImage().getH()).intValue());
	         
        	File cropper = new File(location);
        	ImageIO.write(out, imageExtension, cropper);
	     
        	String state = BlobManager.getInstance().uploadProductBlob(location,imageName, "");//PENDIENTE
	    	if(state!=null)
	    	{
	    		promotion.getImage().setPath(state);
	    	}
	    	else model.addAttribute(Constants.errormsg,"Something was wrong");
	    	
	    	model.addAttribute("crop", false);   
	        model.addAttribute("imageUrl", state);
	    	
	    	cropper.delete();
	// }
	        return state;
    //    if(request.getRequestURI().contains("add"))
     //   	return "promotion/add :: resultsList";
     //   else return "promotion/edit";
   }
}
