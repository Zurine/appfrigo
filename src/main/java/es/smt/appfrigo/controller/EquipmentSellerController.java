package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import es.smt.appfrigo.bean.SelectSeller;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.SellerManager;
import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.ISellerService;

@Controller
@RequestMapping("/equipment/seller")
public class EquipmentSellerController {

	private String folder = "equipment/seller";
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private ISellerService sellerService;

	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("id") int id,Model model) {	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.getSellers(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<SellerDTO> result = ParseJSON.getInstance().getSellerDTOList(r.getResponse());
					
					model.addAttribute("sellerList", result);
					
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				model.addAttribute("current-b", session.getAttribute("current-b"));
				model.addAttribute("businessId", id);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView(folder+"/list");
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("id") int id,Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				List<SellerDTO> sellerList = null;
				SelectSeller data = new SelectSeller();
				ResponseDTO r = sellerService.list(1,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					sellerList = ParseJSON.getInstance().getSellerDTOList(r.getResponse());
					sellerList = SellerManager.getInstance().getNobusiness(sellerList);
					
					BusinessDTO business = null;
					if(session.getAttribute("business") == null)
					{
						r = businessService.get(id,false, user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							business = ParseJSON.getInstance().getBusinessDTO(r.getResponse());
							session.setAttribute("business", business);
						}
					}
					else business = (BusinessDTO)session.getAttribute("business");
					Default e = (Default)session.getAttribute("current-b");
					model.addAttribute("current-b", e);
					data.setBusinessId(business.getId());
					data.setBusinessName(business.getName());
					data.setSellers(new ArrayList<Integer>());
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
			
				model.addAttribute("selected", data);
				model.addAttribute("sellerList", sellerList);


			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(SelectSeller selected,BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes) {	
		
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
					 if(selected!=null && selected.getSellers()!=null && selected.getSellers().size()>0)
					 {

						 ResponseDTO r = businessService.setSeller(selected.getSellers(),selected.getBusinessId(), user.getToken());
						 if(r.getError().getCode() == Constants.codeOK)
						 {
							 redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully Saved");
							 
							 return new ModelAndView("redirect:/"+folder+"/list?id="+selected.getBusinessId());
						 }
						 else model.addAttribute(Constants.errormsg, r.getError().getDesc()); 
					 }
					 else model.addAttribute(Constants.errormsg, "You have to choose an equipment");
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else return new ModelAndView(Constants.logoutRedirect);
		 }
		 model.addAttribute("selected", selected);
		 
		 return new ModelAndView("redirect:/"+folder+"/add?id="+selected.getBusinessId());
	 }
}
