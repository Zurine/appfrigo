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

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Beacon;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.BeaconConverter;
import es.smt.appfrigo.manager.EquipmentManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.BeaconDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBeaconService;
import es.smt.appfrigo.service.IBusinessService;

@Controller
@RequestMapping("/beacon")
@SessionAttributes(value =  {"businessList"})
public class BeaconController {

private String folder = "beacon";
	
	@Autowired
	private IBeaconService beaconService;
	
	@Autowired
	private IBusinessService businessService;

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Beacon> result = new ArrayList<Beacon>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = beaconService.getList(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getBeaconList(r.getResponse());
					
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
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}
		model.addAttribute("beaconList", result);
		
		return new ModelAndView(folder+"/list");
	 }
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id, Model model) {	
		
		Beacon beacon = new Beacon();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = beaconService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					beacon = BeanManager.getInstance().getBeacon(r.getResponse());
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
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}
		model.addAttribute("beacon", beacon);
		
		
		return new ModelAndView(folder+"/details");
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.listMini(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("businessList", EquipmentManager.getInstance().getActive(ParseJSON.getInstance().getBusinessMiniDTOList(r.getResponse())));

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("beacon", new Beacon());
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Beacon beacon, BindingResult bindingResult,ModelMap model) {	
		
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
				BeaconDTO dto = BeaconConverter.getInstance().beanToDto(beacon);
				dto.setEnterpriseId(user.getToken().getEnterpriseId());
//				dto.setBusiness(null);
				try {
					ResponseDTO r = beaconService.add(dto, user.getToken());
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
				model.addAttribute(Constants.errormsg, Constants.loginError); 
				return new ModelAndView("redirect:/login");
			}
		 }
		 
		 return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model ) {	
		
		Beacon beacon = new Beacon();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = beaconService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					beacon = BeanManager.getInstance().getBeacon(r.getResponse());
					model.addAttribute("beacon", beacon);
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
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}

		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Beacon beacon, BindingResult bindingResult,ModelMap model) {	
		
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
				 BeaconDTO dto = BeaconConverter.getInstance().beanToDto(beacon);
			
				try {
					ResponseDTO r = beaconService.edit(dto, user.getToken());
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
				model.addAttribute(Constants.errormsg, Constants.loginError); 
				return new ModelAndView("redirect:/login");
			}
		 }
		 
		 
		 return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = beaconService.activate(state, id, user.getToken());
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
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
}
