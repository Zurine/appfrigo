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
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.BusinessProduct;
import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.ProdBusDetail;
import es.smt.appfrigo.bean.ProdBusMini;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.ProductManager;
import es.smt.appfrigo.model.CompoundProductDTO;
import es.smt.appfrigo.model.ProductBusinessDTO;
import es.smt.appfrigo.model.ProductDTO;
import es.smt.appfrigo.model.ProductMiniDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IProductService;
import es.smt.appfrigo.utils.ProductUtil;

@Controller
@SessionAttributes(value =  {"productList", "compoundList"})
@RequestMapping("/equipment/product")
public class EquipmentProductController {
	
	private String folder = "equipment/product";
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private HttpSession session;
	
	private List<ProductMiniDTO> getCompoundList(int businessId)
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
					r = businessService.listProductsMini(businessId, user.getToken());
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("id") String id,Model model) {	
		
		BusinessProduct business = new BusinessProduct();
		List<ProdBusDetail> result = new ArrayList<ProdBusDetail>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				model.addAttribute("businessId", id);
				ResponseDTO r = businessService.listProducts(Integer.parseInt(id), user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getProdBusDetailList(r.getResponse());
					business.setProducts(result);
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				business.setBusiness(Integer.parseInt(id));
				model.addAttribute("current-b", session.getAttribute("current-b"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("data", business);
		
		return new ModelAndView(folder+"/list");
		
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("product") int product,@RequestParam("business") int business,Model model,RedirectAttributes redirectAttributes)  {	
		
		ProductDTO sp = new ProductDTO();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(product!=0 && business !=0)
			{
				try {
					ResponseDTO r = businessService.getProduct(business,product,true, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						sp =  ParseJSON.getInstance().getProductDTO(r.getResponse());
						model.addAttribute("product", sp);
						model.addAttribute("business", business);
						model.addAttribute("current-b", session.getAttribute("current-b"));
						
						return new ModelAndView(folder+"/details");
					}
					else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else redirectAttributes.addFlashAttribute(Constants.errormsg,Constants.wrong);
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView("redirect:/"+folder+"/list?id="+business);
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("id") int id,Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(id!=0)
			{
				try {
					model.addAttribute("current-b", session.getAttribute("current-b"));
					ResponseDTO r = productService.listByCategories(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Category> products =  ParseJSON.getInstance().getCategoryDTOList(r.getResponse());
						
						r = businessService.listProductsMini(id, user.getToken());
						List<ProdBusMini> myProducts = null;
						if(r.getError().getCode() == Constants.codeOK)
							 myProducts =  ParseJSON.getInstance().getProdBusMiniList(r.getResponse());
						
						if(myProducts!=null && myProducts.size() > 0)
							products = ProductUtil.getInstance().removeProducts(myProducts, products);
					
						SelectProduct sp = new SelectProduct();
						r = businessService.getBusinessIva(id, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
							sp.setIva(r.getResponse() instanceof Double ? (Double)r.getResponse()  : (Integer)r.getResponse());
						
						sp.setSellable(true);
						sp.setProduct(new ProductMiniDTO());
						sp.setBusiness(id);
						sp.setActive(true);
						
						model.addAttribute("businessId", id);
						model.addAttribute("productList", products);
						
						
						//COMPOUND 
						 List<ProductMiniDTO> list = getCompoundList(id);
						 model.addAttribute("compoundList", list);
						 
						 sp.setStringSimple(new ObjectMapper().writer().writeValueAsString(products));
						 sp.setStringCompound(new ObjectMapper().writer().writeValueAsString(list));
						 model.addAttribute("product", sp);

						 return new ModelAndView(folder+"/add");
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute(Constants.errormsg, Constants.wrong);

		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST) 
	public ModelAndView add(@Valid SelectProduct product,BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes)  {
		
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
				 if(product.getBusiness()!=0)
				 {
					try {
						boolean completed = true;
						if(!product.isUpdate()){
							
							if(product.getProducts()!=null && product.getProducts().size()>0)
							{
								List<ProductBusinessDTO> list = new ArrayList<ProductBusinessDTO>();
								
								ProductBusinessDTO p =null;
								
								for(Integer i : product.getProducts())
								{
									if(i != 0){
										p = new ProductBusinessDTO();
										p.setProductId(i);
										p.setActive(product.isActive());
										p.setPrice(product.getPrice());
										p.setStock(product.getStock());
										p.setMinStock(product.getMinStock());
										p.setIva(product.getIva());
										if(product.getType() == 0)
											p.setSellable(product.isSellable());
										else {
											p.setSellable(true);
											List<Integer> c = new ArrayList<Integer>();
											for(List<Integer> j:product.getComponents())
											{
												if(j == null){
													completed = false;
													break;
												}
												else c.addAll(j);
											}
											p.setComposition(c);
										}
										list.add(p);
									}
								}
								if(completed){
									ResponseDTO r = businessService.addProduct(list,product.getBusiness(), user.getToken());
									if(r.getError().getCode() == Constants.codeOK)
									{
										redirectAttributes.addFlashAttribute(Constants.infomsg,"Successfully Saved");
										session.setAttribute("step", 3);
										
										if(product.isNext())
											 return new ModelAndView("redirect:/equipment/product/add?id="+product.getBusiness());
										else return new ModelAndView("redirect:/"+folder+"/list?id="+ product.getBusiness() +"&state="+Constants.active);
										
									}
									else model.addAttribute(Constants.errormsg, r.getError().getDesc());
								}
								else {
									product.setProducts(new ArrayList<Integer>());
									product.setPrice(0);
									model.addAttribute(Constants.errormsg, "You must choose product composition");
								}
							}
							
						}
						else{
							ProductBusinessDTO p = new ProductBusinessDTO();
							 if(product.getProduct()!=null)
							 {
									p.setProductId(product.getProduct().getId());
									p.setActive(product.isActive());
									p.setPrice(product.getPrice());
									p.setStock(product.getStock());
									p.setMinStock(product.getMinStock());
									p.setIva(product.getIva());
									p.setSellable(product.isSellable());
									
									if(product.getType() == 0){
										p.setIva(product.getIva());
								
									}
									else{
										List<Integer> c = new ArrayList<Integer>();
										
										if(product.getComponents()!=null && product.getComponents().size() > 0){
											for(List<Integer> i:product.getComponents())
											{
												c.addAll(i);
											}
											p.setComposition(c);
										}
									}
									ResponseDTO r = businessService.editProduct(p, product.getBusiness(), user.getToken());
									if(r.getError().getCode() == Constants.codeOK)
									{
										return new ModelAndView("redirect:/"+folder+"/list?id="+product.getBusiness());
									}
									else model.addAttribute(Constants.errormsg, r.getError().getDesc());
							 }
						}
						
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 else model.addAttribute(Constants.errormsg, Constants.errormsg);
			}
			else return new ModelAndView(Constants.logoutRedirect);
		 }
		 
		 model.addAttribute("current-b", session.getAttribute("current-b"));
		 
//		 model.addAttribute(Constants.errormsg, Constants.wrong);
		 
		 model.addAttribute("product", product);
		 
		 return new ModelAndView(folder+"/add"); 
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
					
					model.addAttribute("current-b", session.getAttribute("current-b"));
					model.addAttribute("product", product);
					

				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return folder+"/add :: compound-fragment";
	}
	
	
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
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("product") int product,@RequestParam("business") int business, Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(product!=0 && business != 0)
			{
				try {
					ResponseDTO r = businessService.activateProduct(state, product, business, user.getToken());
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
			else redirect.addFlashAttribute(Constants.errormsg, Constants.wrong);
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("current-b", session.getAttribute("current-b"));

//		return new ModelAndView("redirect:/"+folder+"/list?id="+business);
//		if(request.getRequestURI().contains("list"))
//			return new ModelAndView("redirect:/"+folder+"/list?id="+business);
//	    else return new ModelAndView("redirect:/"+folder+"/get?product="+product+"&business="+business);
		
		if(request.getHeader("Referer").contains("list"))
    		return new ModelAndView("redirect:/"+folder+"/list?id="+business);
	    else return new ModelAndView("redirect:/"+folder+"/get?product="+product+"&business="+business);
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("product") int product,@RequestParam("business") int business,Model model,RedirectAttributes redirectAttributes) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			if(product!=0 && business !=0)
			{
				try {
					model.addAttribute("current-b", session.getAttribute("current-b"));
					ResponseDTO r = businessService.getProduct(business,product, false,user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						SelectProduct sp = BeanManager.getInstance().getSelectProduct(r.getResponse());
						sp.setBusiness(business);
						sp.setUpdate(true);

						if(sp.getComponents() != null && sp.getComponents().size()>0)
						{
							List<List<Integer>> component = sp.getComponents();
							sp.setType(1);
							sp = getComposition(product,business,sp,user.getToken());
							sp.setComponents(component);
						}	
						
						model.addAttribute("product", sp);
						return new ModelAndView(folder+"/add");
					}
					else
					{
						redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView("redirect:/"+folder+"/list?id="+business);

	 }

	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("product") int product,@RequestParam("business") int business, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.deleteProduct(business, product,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.infomsg,"Product successfully deleted");

					return new ModelAndView("redirect:/"+folder+"/list?id="+business);
				}
				else redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("current-b", session.getAttribute("current-b"));

		if(request.getHeader("Referer").contains("list"))
    		return new ModelAndView("redirect:/"+folder+"/list?id="+business);
	    else return new ModelAndView("redirect:/"+folder+"/get?product="+product+"&business="+business);
	}
	
	@RequestMapping(value = "/updateProducts", method = RequestMethod.POST) // @RequestParam("id") int id,
	public ModelAndView updateProducts(@Valid BusinessProduct stock,BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes)  {
		
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
				 if(stock.getProducts()!=null)
				 {
					try {
						List<ProductDTO> list = new ArrayList<ProductDTO>();
						ProductDTO p = null;
						for(ProdBusDetail s :stock.getProducts()){
							p = new ProductDTO();
							p.setId(s.getProduct().getId());
							p.setPrice(s.getPrice());
							p.setMinStock(s.getMinStock());
							p.setIva(s.getTax());
							list.add(p);
						}
						
						ResponseDTO r = businessService.updateProducts(list,stock.getBusiness(), user.getToken());
						if(r.getError().getCode() != Constants.codeOK)
						{
							model.addAttribute(Constants.errormsg, r.getError().getDesc());
						}
						model.addAttribute("current-b", session.getAttribute("current-b"));
						
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 }
				 else model.addAttribute(Constants.errormsg, Constants.errormsg);
			}
			else return new ModelAndView(Constants.logoutRedirect);
		 }
		 
		 model.addAttribute("data", stock);
		 
		 return new ModelAndView(folder+"/list");
	}
	

}
