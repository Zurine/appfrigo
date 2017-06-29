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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ProductManager;
import es.smt.appfrigo.model.BusinessTypeDTO;
import es.smt.appfrigo.model.CompoundProductDTO;
import es.smt.appfrigo.model.ProductCompositionDTO;
import es.smt.appfrigo.model.ProductMiniDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IProductService;
import es.smt.appfrigo.utils.ProductUtil;

@Controller
@SessionAttributes(value =  {"productList", "compoundList"})
@RequestMapping("/equipmentType")
public class EquipmentTypeController {

	private String folder = "equipmentType";
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IProductService productService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<BusinessTypeDTO> result = new ArrayList<BusinessTypeDTO>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.listBusinessType(Constants.all, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getBusinessTypeDTOList(r.getResponse());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("equipmentList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView products(@RequestParam("id") int id, Model model) {	
		
		List<ProductMiniDTO> result = new ArrayList<ProductMiniDTO>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.productsByEquipmentType(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("id", id);
		model.addAttribute("productList", result);
		
		return new ModelAndView(folder+"/products");
	}
	
	
	
	
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addProduct(@RequestParam("id") int id, Model model) {	
		
		List<Category> result = new ArrayList<Category>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = productService.listByCategories(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getCategoryDTOList(r.getResponse());
					
					List<ProductMiniDTO> typeProducts = new ArrayList<ProductMiniDTO>();
					r = businessService.productsByEquipmentType(id, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						typeProducts = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
					}
					
					result = ProductUtil.getInstance().removeProductsType(typeProducts, result);
					
					SelectProduct sp = new SelectProduct();
					sp.setBusiness(id); //Equipment Type
					sp.setProduct(new ProductMiniDTO());
					sp.setSellable(true);
					sp.setStringSimple(new ObjectMapper().writer().writeValueAsString(result));
//					sp.setStringCompound(new ObjectMapper().writer().writeValueAsString(list));
					
					//COMPOUND 
					 List<ProductMiniDTO> list = getCompoundList(id);
					 model.addAttribute("compoundList", list);
					 
					 sp.setStringCompound(new ObjectMapper().writer().writeValueAsString(list));
					
					
					model.addAttribute("product", sp);
					model.addAttribute("id", id);
					model.addAttribute("productList", result);

				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		

		
		return new ModelAndView(folder+"/addProduct");
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(SelectProduct product, Model model) {	
		
//		List<ProductMiniDTO> result = new ArrayList<ProductMiniDTO>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				
				if(product!=null)
				{
					ProductCompositionDTO pc = new ProductCompositionDTO();
					List<ProductCompositionDTO> list = new ArrayList<ProductCompositionDTO>();

					boolean completed = true;
					if(product.getProducts()!=null)
					{
						if(product.getComponents()!=null){
							pc = new ProductCompositionDTO();
							pc.setProductId(product.getProducts().get(0));
							pc.setPrice(product.getPrice());
							pc.setComposition(new ArrayList<ProductMiniDTO>());
							for(List<Integer> p:product.getComponents())
							{
					
								if(p!=null){
									for(Integer i:p)
									{
										
										if(i == null){
											completed = false;
											break;
										}
										else{
											ProductMiniDTO mini = new ProductMiniDTO();
											mini.setId(i);
											pc.getComposition().add(mini);
										}
										
									}
								}
								else {
									completed = false;
									break;
								}

							}
							list.add(pc);
						}
						else{
							for (Integer i: product.getProducts()) {
								if(i!=0){
									pc = new ProductCompositionDTO();
									pc.setProductId(i);
									pc.setPrice(product.getPrice());
									list.add(pc);
								}
							}
						}
					}
					if(completed){
						System.out.println("la lista que voy a enviar---> " + list.toString());
						ResponseDTO r = businessService.addProductToEquipmentType(product.getBusiness(), list, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
//							result = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
							
							return new ModelAndView("redirect:/"+folder+"/products?id="+product.getBusiness());
						}
						else  model.addAttribute(Constants.errormsg, r.getError().getDesc());

					}
					else{
						
						model.addAttribute(Constants.errormsg, "You must choose product composition");
					}
				
				}
				
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
//		product = new SelectProduct();/
//		product.setBusiness(id);
		
		
		product.setProducts(new ArrayList<Integer>());
		product.setPrice(0);
		model.addAttribute("product", product);
//		model.addAttribute("id", id);
//		model.addAttribute("productList", result);
		
		return new ModelAndView(folder+"/addProduct");
	}
	
	@RequestMapping(value = "/componets", method = RequestMethod.POST)
	public String getComponets(@Valid SelectProduct product, BindingResult bindingResult,ModelMap model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(product!=null && product.getProducts()!=null)
			{
				try {
					
					int id = 0;
					if(product.getProducts()!=null && product.getProducts().size() > 0){
					
						boolean found = false;
						int cont = 0;
						while(!found && cont < product.getProducts().size()){
							if(product.getProducts().get(cont)!=null){
								id = product.getProducts().get(cont);
								found=true;
							}
							cont++;
						}
					}
					if(id!=0){
						SelectProduct sp = getComposition(id,product.getBusiness(),product,user.getToken());
						product.setComponents(sp.getComponents());
						product.setComposition(sp.getComposition());
						product.setPrice(sp.getPrice());
					}
					
					model.addAttribute("product", product);
					

				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return folder+"/addProduct :: compound-fragment";
	}

	
	
	private List<ProductMiniDTO> getCompoundList(int id)
	{
		List<ProductMiniDTO> products = new ArrayList<ProductMiniDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = productService.listComposed(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					products =  ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());							
					r = businessService.productsByEquipmentType(id, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<ProductMiniDTO> myProducts = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
						
						products = ProductUtil.getInstance().getCompound(products, myProducts);
					}
				}

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return products;
	}

	
//	@RequestMapping(value = "/addCompound", method = RequestMethod.GET)
//	public ModelAndView addCompound(@RequestParam("id") int id,Model model) {	
//		
//		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if(user!=null && user.isCredentialsNonExpired())
//		{	
//			if(id!=0)
//			{
//				try {
//							
//					ResponseDTO r = productService.listComposed(user.getToken());
//					if(r.getError().getCode() == Constants.codeOK)
//					{
//						List<ProductMiniDTO> products =  ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
//						
//						r = businessService.productsByEquipmentType(id, user.getToken());
//						if(r.getError().getCode() == Constants.codeOK)
//						{
//							List<ProductMiniDTO> myProducts = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
//
//							products = ProductUtil.getInstance().getCompound(products, myProducts);
//
//							model.addAttribute("compoundList", products);
//						}
//					}
//					SelectProduct sp = new SelectProduct();
//					sp.setBusiness(id);
//				
//					model.addAttribute("id", id);
//					model.addAttribute("stringList", new ObjectMapper().writer().writeValueAsString(sp.getComposition()));
//					model.addAttribute("product", sp);
//					
//					return new ModelAndView(folder+"/addCompound");
//				
//					} catch (JsonProcessingException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//				}
//			}
//		}
//		else return new ModelAndView(Constants.logoutRedirect);
//		
//		return new ModelAndView(folder+"/list");
//	 }
////	
//	@RequestMapping(value={"/addProduct"}, params={"getComponets"}) 
//	public ModelAndView getComponets(final SelectProduct product, final BindingResult bindingResult,Model model, HttpServletRequest request) {
//		
//		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if(user!=null && user.isCredentialsNonExpired())
//		{	
//			if(product!=null && product.getProducts()!=null)
//			{
//				try {
//				
//					SelectProduct sp = getComposition(product.getProducts().get(0),product.getBusiness(),product,user.getToken());
//					product.setComponents(sp.getComponents());
//					product.setComposition(sp.getComposition());
//					product.setPrice(sp.getPrice());
//					
//					model.addAttribute("product", product);
//
//				} catch (JsonProcessingException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	
////        if(request.getRequestURI().contains("add"))
//        	return new  ModelAndView(folder+"/addCompound");
////        else return new ModelAndView(folder+"/editC");
//	}
	
	private SelectProduct getComposition(int productId, int businessId, SelectProduct product, TokenDTO token) throws JsonProcessingException, IOException
	{
		ResponseDTO r = businessService.getProductComposition(productId,businessId, token);
		if(r.getError().getCode() == Constants.codeOK)
		{
			CompoundProductDTO cp = ParseJSON.getInstance().getCompoundProductDTO(r.getResponse());
			
			product = ProductManager.getInstance().getComposition(cp.getComposition(),product);
			product.setPrice(cp.getPrice());
		}
		
		return product;
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam("id") int id,@RequestParam("type") int type, Model model,RedirectAttributes redirectAttributes) {	
		
		List<ProductMiniDTO> result = new ArrayList<ProductMiniDTO>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.deleteProductToEquipmentType(type,id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully deleted");
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		SelectProduct product = new SelectProduct();
		product.setBusiness(id);
		model.addAttribute("product", product);
		model.addAttribute("id", id);
		model.addAttribute("productList", result);
		
		return new ModelAndView("redirect:/equipmentType/products?id="+type);
	}
	
}
