package es.smt.appfrigo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import es.smt.appfrigo.bean.BusinessNano;
import es.smt.appfrigo.bean.ProdBusMini;
import es.smt.appfrigo.bean.SaleItem;
import es.smt.appfrigo.bean.SaleMini;
import es.smt.appfrigo.bean.SelectProduct;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.ProductManager;
import es.smt.appfrigo.model.CompoundProductDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SaleMiniDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;

@Controller
@RequestMapping("/sales")
@SessionAttributes(value =  {"equipmentList", "sellerList", "productList"})

public class SalesController {
	
	
	private String folder = "sales";
	
	@Autowired
	private IBusinessService businessService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model ) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			List<ProdBusMini> products = new ArrayList<ProdBusMini>();
			List<SellerDTO> sellers = new ArrayList<SellerDTO>();
			List<BusinessNano> list = new ArrayList<BusinessNano>();
			SaleMini sales = new SaleMini();
			List<SaleItem> si = new ArrayList<SaleItem>();
			
			try {
				ResponseDTO r = businessService.listEquipment(Constants.active,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					list = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
					model.addAttribute("equipmentList", list);
					if(list!=null && list.size()>0)
					{
						products = getProductList(list.get(0).getId(), user.getToken());
						if(products!=null && products.size()>0)
						{
							sellers = getSellerList(list.get(0).getId(), user.getToken());
							if(sellers!=null && sellers.size()>0)
							{
								sales = addProducts(sales);
								si = sales.getItem();
							}
							else model.addAttribute(Constants.wrnmsg,"There are no MSM for this equipment");
						}
						else model.addAttribute(Constants.wrnmsg,"There are no products for this equipment");
					}
					model.addAttribute("productList", products);
					model.addAttribute("sellerList", sellers);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			model.addAttribute("sales", sales);
			model.addAttribute("itemList", si);
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	private SaleMini addProducts(SaleMini sales){
		List<SaleItem> si = new ArrayList<SaleItem>();
		
		for(int i = 0; i<7;i++){
			SaleItem sit = new SaleItem();
			sit.setAmount(1);
			si.add(sit);
			sales.setItem(si);
		}
		
		return sales;
	}
	
	private List<SellerDTO> getSellerList(int businessId, TokenDTO t) throws JsonProcessingException, IOException
	{
		List<SellerDTO> sellers = new ArrayList<SellerDTO>();
		
		ResponseDTO r = businessService.getSellers(businessId,t);
		if(r.getError().getCode() == Constants.codeOK)
		{
			sellers = ParseJSON.getInstance().getSellerDTOList(r.getResponse());
		}
		
		return sellers;
	}
	
	private List<ProdBusMini> getProductList(int businessId, TokenDTO t) throws JsonProcessingException, IOException
	{
		List<ProdBusMini> products = new ArrayList<ProdBusMini>();
		
		ResponseDTO r = businessService.listProductsMini(businessId,t);
		if(r.getError().getCode() == Constants.codeOK)
		{
			products = ParseJSON.getInstance().getProdBusMiniList(r.getResponse());
		}
		
		if(products == null || products.size() == 0)
		{
			products.add(new ProdBusMini(0, "There are no products",false));
		}
		
		return products;
	}
	
	@RequestMapping(value="/addRow") // , params={"addRow"}
	public String addRow(final SaleMini sales, final BindingResult bindingResult,ModelMap model) {
		
		model.addAttribute("sales", addItem(sales));
		
	    return folder+"/add :: fragment-seller";
	}
	
	private SaleMini addItem(SaleMini sales){
		SaleItem si = new SaleItem();
		si.setAmount(1);
		sales.getItem().add(si);;
		
		return sales;
	}
	
	
	@RequestMapping(value="/removeItem") // , params={"addRow"}
	public String removeProduct(final SaleMini sales, final BindingResult bindingResult,ModelMap model) {
		
		
		sales.getItem().remove(sales.getRow());
		SaleMini sales2 = sales;
		
		if(sales.getItem().size() == 0)
			sales2 =	addItem(sales2);
		
		model.addAttribute("sales", sales2);
	    return folder+"/add :: fragment-seller";
	}
	
	@RequestMapping(value="/getProducts") //, params={"getProducts"}
	public String getProducts(final SaleMini sales, final BindingResult bindingResult,ModelMap model) {
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			try {
				List<SellerDTO> s = getSellerList(sales.getBusinessId(), user.getToken());
				if(s == null || s.size() == 0)
					model.addAttribute(Constants.wrnmsg,"There are no MSM for this equipment");
				
				model.addAttribute("sellerList", s);

				List<ProdBusMini> p = getProductList(sales.getBusinessId(), user.getToken());
				if(p == null || p.size() == 0)
					model.addAttribute(Constants.wrnmsg,"There are no products for this equipment");
				
				model.addAttribute("productList", p);
				
				sales.setItem(new ArrayList<SaleItem>());
				List<SaleItem> si = new ArrayList<SaleItem>();
				
				for(int i = 0; i<7;i++){
					SaleItem sit = new SaleItem();
					sit.setAmount(1);
					si.add(sit);
					sales.setItem(si);
				}
				
				

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("sales", sales);
		
		
	    return folder+"/add :: fragment-seller";
	}

	
	
	@RequestMapping(value={"/getComposed"}) // , params={"getComposed"}
	public String getComposed(final SaleMini sale, final BindingResult bindingResult, HttpServletRequest request,ModelMap model) {
		
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			try {
				
				ResponseDTO r = businessService.getProductComposition(sale.getProductId(),sale.getBusinessId(), user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					CompoundProductDTO cp = ParseJSON.getInstance().getCompoundProductDTO(r.getResponse());
					
					SelectProduct product = ProductManager.getInstance().getComposition(cp.getComposition(),new SelectProduct());
					sale.getItem().get(sale.getRow()).setCompositionList(product.getComposition());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		model.addAttribute("sales", sale);
		
		return folder+"/add :: fragment-seller";
	}	
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid SaleMini sale, BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes) {	 //,RedirectAttributes redirectAttributes
		
//		List<ProdBusMini> products = new ArrayList<ProdBusMini>();
//		List<SellerDTO> sellers = new ArrayList<SellerDTO>();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		try {
			if (bindingResult.hasErrors()) 
			{
				model.addAttribute(Constants.errormsg,
					ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
			}
			else
			{
				if(user!=null && user.isCredentialsNonExpired())
				{
					List<SaleMiniDTO> list = new ArrayList<SaleMiniDTO>();
					if(sale.getItem()!=null && sale.getItem().size()>0)
					{
						for(SaleItem si:sale.getItem())
						{
							SaleMiniDTO dto = new SaleMiniDTO();
							if(si.getProductId() != 0)
							{
								dto.setProductId(si.getProductId());
								dto.setGift(si.isGift());
								dto.setAmount(si.getAmount());
								dto.setComponents(si.getComponents());
								list.add(dto);
							}
						}
						if(list.size() == 0)
						{
							model.addAttribute(Constants.errormsg, "There are no products");
						}
						else
						{
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
							ResponseDTO r = businessService.addSaleByAdmin(list, sale.getSellerId(), sale.getBusinessId(),formatter.parse(sale.getDate()+" 12:00"), user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								model.addAttribute(Constants.infomsg,"The sales were added correctly");

//								if(sellers!=null && sellers.size()>0)
//									sale = addProducts(sale);
							}
							else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						}
					}
					else model.addAttribute(Constants.errormsg, "There are no products");
				}
				else return new ModelAndView(Constants.logoutRedirect);
			}
		
//			if(sale!=null && sale.getBusinessId()!=0)
//			{
//				products = getProductList(sale.getBusinessId(), user.getToken());
//				sellers = getSellerList(sale.getBusinessId(), user.getToken());
//				if(sellers!=null && sellers.size()>0)
//					sale = addProducts(sale);
//			}
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("sales", sale);
//		model.addAttribute("productList", products);
//		model.addAttribute("sellerList", sellers);
		 
		return new ModelAndView(folder+"/add");
	}
}