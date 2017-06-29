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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.DistributorContact;
import es.smt.appfrigo.bean.ProductStock;
import es.smt.appfrigo.bean.StockItem;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IOperatorService;

@Controller
@RequestMapping("/operator/stock")
public class StockController {

	private String folder = "operator/stock";
	
	@Autowired
	private IOperatorService operatorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("operatorId") int operatorId,Model model ) {	
		
		int operator = 0;
		List<Default> operators = new ArrayList<Default>();
		List<ProductStock> result = new ArrayList<ProductStock>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				operators = user.getItems();
				operator = operators.get(0).getId();

				ResponseDTO r = operatorService.listProducts(operator,true,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getProductStockList(r.getResponse());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("operatorList", operators);	
		model.addAttribute("updateProduct", new ProductStock());	
		model.addAttribute("products", result);
		model.addAttribute("operatorId", operator);
		
		return new ModelAndView(folder+"/list");
	}
	
	
	@RequestMapping(value = "/listProducts", method = RequestMethod.GET)
	public String listProducts(@RequestParam("operatorId") int operatorId,ModelMap model ) {	
		
		List<ProductStock> result = new ArrayList<ProductStock>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = operatorService.listProducts(operatorId,true,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getProductStockList(r.getResponse());	
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
//				result.add(new ProductStock(2, "", "", 6, 3));
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else return Constants.logoutRedirect;
		
		model.addAttribute("products", result);
		
		return folder+"/list :: fragment-products";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView stock(@RequestParam("id") int operator, Model model) {	
		
		List<ProductStock> result = new ArrayList<ProductStock>();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = operatorService.listProducts(operator,false,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getProductStockList(r.getResponse());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		StockItem item = new StockItem();
		item.setOperator(operator);
		model.addAttribute("stock", item);
		model.addAttribute("productList", result);

		return new ModelAndView(folder+"/stock");
	}
	
	@RequestMapping(value = "/resetStock", method = RequestMethod.GET)
	public ModelAndView resetStock(@RequestParam("id") int operator, Model model,RedirectAttributes redirectAttributes)  {
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = operatorService.resetStock(operator,user.getToken());
				if(r.getError().getCode() != Constants.codeOK)
				{
					redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
				else redirectAttributes.addFlashAttribute(Constants.infomsg, "Stock successfully reset");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView("redirect:/"+folder+"/manage?id="+operator);
	}
	
	@RequestMapping(value = "/updateStock", method = RequestMethod.POST)
	public ModelAndView updateStock(@Valid StockItem stock,BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes)  {
		
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
				 if(stock.getStock()!=0 && stock.getProducts()!=null && stock.getProducts().size()>0)
				 {
					try {
						
						ResponseDTO r = operatorService.updateStock(stock.getStock(), stock.getProducts(),stock.getOperator(), user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							redirectAttributes.addFlashAttribute(Constants.infomsg, "Succesfully updated");
						}
						else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
						
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
		 
		 model.addAttribute(Constants.errormsg, Constants.wrong);
		 
		 return new ModelAndView("redirect:/"+folder+"/manage?id="+stock.getOperator());
	}
	
	@RequestMapping(value = "/equipmentStock", method = RequestMethod.GET)
	public String equipmentStock(@RequestParam("operatorId") int operatorId,@RequestParam("productId") int productId,ModelMap model ) {	
		
		List<ProductStock> result = new ArrayList<ProductStock>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = operatorService.listProductStock(operatorId, productId, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getProductStockList(r.getResponse());	
					model.addAttribute("equipments",result);
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		else return Constants.logoutRedirect;
		
		return folder+"/list :: equipmentList";
	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProduct(ProductStock product, ModelMap model,BindingResult bindingResult,RedirectAttributes redirectAttributes)  {
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (bindingResult.hasErrors()) 
		{
			model.addAttribute(Constants.errormsg,ErrorManager.getInstance().getBindingResultMessageString(bindingResult.getAllErrors()));
		}
		else
		{
			if(user!=null && user.isCredentialsNonExpired())
			{
				try {
					ResponseDTO r = operatorService.updateProduct(product, product.getOperatorId(), user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						redirectAttributes.addFlashAttribute(Constants.infomsg, "Succesfully updated");
					}
					else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
					
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			else return new ModelAndView(Constants.logoutRedirect);
		}
		
		return new ModelAndView("redirect:/"+folder+"/list?operatorId="+product.getOperatorId());
	}
	
	@RequestMapping(value = "/distributors", method = RequestMethod.GET)
	public String search(@RequestParam("operatorId") int operatorId,ModelMap model ) {	
		
		List<DistributorContact> result = new ArrayList<DistributorContact>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				
				ResponseDTO r = operatorService.listDistributor(operatorId,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getDistributorContactList(r.getResponse());	
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return Constants.logoutRedirect;
		
		model.addAttribute("distributorList",result);
		
		return folder+"/list :: distributor-list";
	}

}
