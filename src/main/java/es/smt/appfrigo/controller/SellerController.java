package es.smt.appfrigo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
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

import es.smt.appfrigo.bean.BusinessNano;
import es.smt.appfrigo.bean.Data;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.Registration;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.UserMiniDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.ISellerService;

@Controller
@RequestMapping("/seller")
@SessionAttributes(value =  {"equipmentList"})
public class SellerController {

	private String folder = "seller";
	
	@Autowired
	private ISellerService sellerService;

	@Autowired
	private IBusinessService businessService;
	

	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("state") int state, Model model ) {	
		
		List<SellerDTO> result = new ArrayList<SellerDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = sellerService.list(state,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getSellerDTOList(r.getResponse());
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
		model.addAttribute("sellerList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id,Model model ,RedirectAttributes redirectAttributes) {	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = sellerService.get(id, true,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					SellerDTO result = ParseJSON.getInstance().getSellerDTO(r.getResponse());
//					session.setAttribute("business", result);
					model.addAttribute("seller", result);
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
		
		return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("businessId") int businessId, Model model) {	
	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			SellerDTO s = new SellerDTO();
			s.setBusiness(new ArrayList<BusinessMiniDTO>());
			s.setBusinessList(new ArrayList<Integer>());
			if(businessId!=0)
				s.getBusinessList().add(businessId);
			s.setUser(new UserMiniDTO());
			s.setGps(true);
			s.setActive(true);
			s.setRegistration(new Registration());
			model.addAttribute("seller",s);
			model.addAttribute("businessId",businessId);
			
			try {
				ResponseDTO r = businessService.listEquipment(Constants.active,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<BusinessNano> result = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
					model.addAttribute("equipmentList", result);
				}
				/**Writing the title, depending if is a new equipment of is an edition**/
				model.addAttribute("title",setTitle(s));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	 }
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid SellerDTO seller, BindingResult bindingResult,ModelMap model) {	
		
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

					 seller.setBusiness(new ArrayList<BusinessMiniDTO>());
					 if( seller.isActive() &&  seller.getBusinessList()!=null && seller.getBusinessList().size()>0){
						 for(Integer b:seller.getBusinessList())
						 {
							 seller.getBusiness().add(new BusinessMiniDTO(b));
						 }
					 }
						
					 if(seller.getId() == 0){
						 
						 if(seller.getRegistration().getPassword()!=null && seller.getRegistration().getRepPassword()!=null && !seller.getRegistration().getPassword().isEmpty() && !seller.getRegistration().getRepPassword().isEmpty()  && seller.getRegistration().getPassword().equals(seller.getRegistration().getRepPassword()))
						 {
							 seller.setUser(new UserMiniDTO(0, "", seller.getRegistration().getPassword(),  seller.getRegistration().getEmail(), 0));
							 r = sellerService.add(seller, user.getToken());
							 if(r.getError().getCode() == Constants.codeOK) {
								//Save to show the assistant
								session.setAttribute("step", null);
								if(seller.getBusiness()!=null)
									session.setAttribute("currentBusiness", seller.getBusiness().get(0).getId());
								
								return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
							 } 
							 else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						 }
						 else model.addAttribute(Constants.errormsg,"Passwords don't match"); 
					 }
					 else{
				 
						 seller.setUser(new UserMiniDTO(0, "", "", seller.getRegistration().getEmail(),user.getToken().getEnterpriseId()));

						 r = sellerService.edit(seller, user.getToken());
						 if(r.getError().getCode() == Constants.codeOK)
							return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
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
		 
		/**Writing the title, depending if is a new equipment of is an edition**/
		model.addAttribute("title",setTitle(seller));
		model.addAttribute("seller",seller);
		 
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView upload( Model model) {	
	
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.listEquipment(Constants.active,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<BusinessNano> result = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
					model.addAttribute("equipmentList", result);
				}
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			SellerDTO s = new SellerDTO();
			s.setUser(new UserMiniDTO());
			s.setFile(new Image());
			
			model.addAttribute("result", new ArrayList<Data>());
			model.addAttribute("seller",s);
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/upload");
	 }
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(SellerDTO seller, BindingResult bindingResult,ModelMap model) {	
		
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
				if(seller.getFile()!=null )
				{
					try {
					   if (!seller.getFile().getFile().isEmpty()) {
							   
						   File file = new File(session.getServletContext().getRealPath("/") + "/" + seller.getFile().getFile().getName());
						   FileUtils.writeByteArrayToFile(file, seller.getFile().getFile().getBytes());
						   List<SellerDTO> result = new ArrayList<SellerDTO>();
						   
						   Scanner scanner = new Scanner(file);
						   while (scanner.hasNextLine()) {
							   SellerDTO s = SerializationUtils.clone(seller);
								String line = scanner.nextLine();
								line = line.substring(1, line.length()-1);
								String [] split = line.split(";");
								s.setName(split[0]);
								s.setSurname(split[1]);
								s.getUser().setEmail(split[2]);
								result.add(s);
						   }
						   
						   if(result.size()>0){
								ResponseDTO r = sellerService.addList(result,user.getToken());
								if(r.getError().getCode() == Constants.codeOK)
								{
									List<Data> response = ParseJSON.getInstance().getDataList(r.getResponse());
									model.addAttribute("result", response);
								}
								else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						   }
					   }
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else return new ModelAndView(Constants.logoutRedirect);
		 }
		 model.addAttribute("seller",seller);
		 
		 return new ModelAndView(folder+"/upload");
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model,RedirectAttributes redirectAttributes) {	
		
		SellerDTO seller = new SellerDTO();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = sellerService.get(id, false, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					seller = ParseJSON.getInstance().getSellerDTO(r.getResponse());
					seller.setRegistration(new Registration());
					seller.getRegistration().setEmail(seller.getUser().getEmail());
					if(seller.getBusiness()!= null && seller.getBusiness().size()>0)
					{
						seller.setBusinessList(new ArrayList<Integer>());
						for(BusinessMiniDTO bm:seller.getBusiness())
						{
							seller.getBusinessList().add(bm.getId());
						}
					}
					
					r = businessService.listEquipment(Constants.active,user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<BusinessNano> result = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
						model.addAttribute("equipmentList", result);
					}
					
					/**Writing the title, depending if is a new equipment of is an edition**/
					model.addAttribute("title",setTitle(seller));
					model.addAttribute("registrationBean", new Registration());
					model.addAttribute("seller", seller);
					
					return new ModelAndView(folder+"/add");
				}
				else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirectAttributes, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = sellerService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirectAttributes.addFlashAttribute(Constants.infomsg,"Successfully deleted");
					
					return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
				}
				else
				{
					boolean res = ParseJSON.getInstance().getBoolean(r.getResponse());
					if(!res)
						redirectAttributes.addFlashAttribute(Constants.errormsg,"You cannot delete this item");
					else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		if(request.getRequestURI().contains("list"))
			return new ModelAndView(folder+"/list");
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
	}
	
	private String setTitle(SellerDTO s){
		String title = "";
		if(s.getId() == 0)
			title = "Create New MSM";
		else title = "Edit " + s.getName()+ " " + s.getSurname();
		
		return title;
	}
}
