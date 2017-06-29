package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Channel;
import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.Distributor;
import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ChannelManager;
import es.smt.appfrigo.manager.DistributorManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.RegionManager;
import es.smt.appfrigo.model.BusinessDTO;
import es.smt.appfrigo.model.BusinessDetailDTO;
import es.smt.appfrigo.model.BusinessTypeDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IChannelService;
import es.smt.appfrigo.service.IDistributorService;
import es.smt.appfrigo.service.IOperatorService;
import es.smt.appfrigo.service.IRegionService;
import es.smt.appfrigo.service.ISettingsService;

@Controller
@RequestMapping("/equipment")
@SessionAttributes(value =  {"operatorList", "taxList"})
public class EquipmentController {

	private String folder = "equipment";


	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IChannelService channelService;

	@Autowired
	private IRegionService regionService;
	
	@Autowired
	private IDistributorService distributorService;
	
	@Autowired
	private ISettingsService settingsService;
	
	@Autowired
	private IOperatorService operatorService;
	
	@Autowired
	private HttpSession session;

	/*************************************************************************COMBOS******************************************************************/
	
	
	@ModelAttribute("channelList")
	public List<Channel> getChannels() {
	    return getChannelList();
	}
	
	private List<Channel> getChannelList()
	{
		List<Channel> list = new ArrayList<Channel>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = channelService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					list = ParseJSON.getInstance().getChannelDTOList(r.getResponse());
					list = ChannelManager.getInstance().getActive(list);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	@ModelAttribute("regionList")
	public List<Region> getRegions() {
	    return getRegionList();
	}
	
	private List<Region> getRegionList()
	{
		List<Region> list = new ArrayList<Region>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = regionService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					list =  ParseJSON.getInstance().getRegionDTOList(r.getResponse());
					list = RegionManager.getInstance().getActive(list);
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@ModelAttribute("typeList")
	public List<BusinessTypeDTO> getTypes() {
	    return getTypesList();
	}
	
	private List<BusinessTypeDTO> getTypesList()
	{
		List<BusinessTypeDTO> list = new ArrayList<BusinessTypeDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.listBusinessType(Constants.active,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					list = ParseJSON.getInstance().getBusinessTypeDTOList(r.getResponse());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	/*************************************************************************COMBOS******************************************************************/
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("state") int state, Model model) {	
		
		System.out.println("list");
		List<BusinessDetailDTO> result = new ArrayList<BusinessDetailDTO>();
		long A = new Date().getTime();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				long A1 = new Date().getTime();
				ResponseDTO r = businessService.list(state,user.getToken());
				long A2 = new Date().getTime();
				System.out.println("SERVER TIME -> " + (A2-A1));
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getBusinessDetailsDTOList(r.getResponse());
					
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("businessList", result);
		model.addAttribute("state", state);
		long B = new Date().getTime();
		System.out.println("TOTAL TIME -> " + (B-A));

		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id,Model model,RedirectAttributes redirectAttributes) {

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.get(id,true, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					BusinessDTO result = ParseJSON.getInstance().getBusinessDTO(r.getResponse());
					session.setAttribute("current-b", new Default(result.getId(),result.getName()));
					model.addAttribute("equipment", result);
					session.setAttribute("business", result);
					
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
	public ModelAndView add(@RequestParam("operatorId") int operatorId,Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			try {
				
				BusinessDTO b = new BusinessDTO(new Channel(),new Region(),new BusinessTypeDTO(),new Distributor());
				List<Operator> operator = new ArrayList<Operator>();
				/**Get defaul inative time**/
				ResponseDTO r = settingsService.getInativeTime(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					b.setInactiveTime(Integer.parseInt((String)r.getResponse()));
				}
				/**List of active operators**/
				r = operatorService.list(Constants.active,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					operator = ParseJSON.getInstance().getOperatorDTOList(r.getResponse());
				}	
				
				/**List of taxes**/
				r = businessService.listTaxes(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<Default> taxes = ParseJSON.getInstance().getDefaultDTOList(r.getResponse());
					model.addAttribute("taxList",taxes);
					Default d = new Default();
					if(taxes.size() == 1)
						d.setId(taxes.get(0).getId());
					b.setTax(d);
				}			
				
				/**Setting the selected operator. If the previous action was new operator, the operator will be selected**/
				b.setOperator(new Operator(operatorId));
				b.setAddProducts(true);
				b.setActive(true);
				
				model.addAttribute("operatorList",operator);
				model.addAttribute("distributorList", new ArrayList<Distributor>());
				model.addAttribute("equipment", b);
				/**Writing the title, depending if is a new equipment of is an edition**/
				model.addAttribute("title",setTitle(b));
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	private String setTitle(BusinessDTO b){
		String title = "";
		if(b.getId() == 0)
			title = "Create New Equipment";
		else title = "Edit " + b.getName();
		
		return title;
	}
	
	@RequestMapping(value="/getDistributor") //, params={"getDistributor"}
	public String  getDistributor(final BusinessDTO equipment ,final BindingResult bindingResult,ModelMap model, HttpServletRequest request) {
		
		List<Distributor> list = new ArrayList<Distributor>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			try {
				list = getDistributor(equipment,user.getToken());
				
				if(list == null || list.size() == 0)
				{
					list.add(new Distributor(0, "There are not distributors"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{}

		
		model.addAttribute("equipment", equipment);
		model.addAttribute("distributorList", list);
		
		return folder+"/add :: fragment-distributor";
	}
	
	private List<Distributor> getDistributor(BusinessDTO equipment, TokenDTO t) throws JsonProcessingException, IOException
	{
		List<Distributor> list = new ArrayList<Distributor>();
		
		if(equipment!=null && equipment.getRegion()!=null && equipment.getRegion().getId()!=0)
		{
			ResponseDTO r = distributorService.listByRegion( equipment.getRegion().getId(), t);
			if(r.getError().getCode() == Constants.codeOK)
			{
				list = ParseJSON.getInstance().getDistributorDTOList(r.getResponse());
				list = DistributorManager.getInstance().getActive(list);
			}
		}
		
		return list;
	}
	
	private String validate(BusinessDTO equipment)
	{
		String message = "";
		 if(equipment==null)
		 {
			 message = "Something was wrong";
		 }
		 else
		 {
			 if(equipment.getChannel()==null || equipment.getChannel().getId() == 0)
			 {
				 message = "Channel must be selected"; 
			 }
			 else if(equipment.getRegion()==null || equipment.getRegion().getId() == 0)
			 {
				 message = "Region must be selected"; 
			 }
			 else if(equipment.getDistributor()==null || equipment.getDistributor().getId() == 0)
			 {
				 message = "Distributor must be selected"; 
			 }
			 else if(equipment.getType()==null || equipment.getType().getId() == 0)
			 {
				 message = "Equipment Type must be selected"; 
			 }
		 }
		 return message;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView  add(@Valid BusinessDTO equipment, BindingResult bindingResult,ModelMap model,RedirectAttributes redirectAttributes) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (bindingResult.hasErrors()) 
		{
			model.addAttribute(Constants.errormsg,
				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
		}
		else
		{
			if(user!=null && user.isCredentialsNonExpired())
			 {
				 try {
					 
					 String message = validate(equipment);
					 if(message.equals(""))
					 {
						 ResponseDTO r = null;
						 if(equipment.getId() == 0){
							 
							 equipment.setEnterpriseId(user.getToken().getEnterpriseId());
							 BusinessDTO b = equipment;
						
							 r =  businessService.add(b, user.getToken());
							 if(r.getError().getCode() == Constants.codeOK)
							 {
								 b = ParseJSON.getInstance().getBusinessDTO(r.getResponse());
								 //Save to show the assistant
								session.setAttribute("step", 2);
								session.setAttribute("currentBusiness", b.getId());
								session.setAttribute("current-b", new Default(b.getId(),b.getName()));
								Default e = (Default)session.getAttribute("current-b");
								System.out.println("ee-> " + e.toString());
							//	session.setAttribute("current-b",b);

								
								redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully Saved");
								
								if(equipment.isNext())
									 return new ModelAndView("redirect:/equipment/seller/add?id="+b.getId());
								else return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
							 }	
							 else model.addAttribute(Constants.errormsg,r.getError().getDesc());
						 } 
						 else{
							r = businessService.edit(equipment, user.getToken());
							
							
							if(r.getError().getCode() == Constants.codeOK)
							{
								redirectAttributes.addFlashAttribute(Constants.infomsg, "Successfully Saved");
								
								return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
							}
							else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						 }
					 }
					 else model.addAttribute(Constants.errormsg,message);
						
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else return new ModelAndView(Constants.logoutRedirect);
		}
		
		if(equipment.getRegion()!=null && equipment.getRegion().getId()!=0)
		{
			 try {
				model.addAttribute("distributorList", getDistributor(equipment,user.getToken()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		model.addAttribute("equipment", equipment);
		/**Writing the title, depending if is a new equipment of is an edition**/
		model.addAttribute("title",setTitle(equipment));
		
		return new ModelAndView(folder+"/add");
	 }
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model) {	
		
		BusinessDTO business = new BusinessDTO();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.get(id,false, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					business = ParseJSON.getInstance().getBusinessDTO(r.getResponse());
					List<Distributor> list = getDistributor(business,user.getToken());
					List<Operator> operator = new ArrayList<Operator>();
					model.addAttribute("distributorList", list);
//					model.addAttribute("taxList",Constants.tax);
					/**List of taxes**/
					r = businessService.listTaxes(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Default> taxes = ParseJSON.getInstance().getDefaultDTOList(r.getResponse());
						model.addAttribute("taxList",taxes);
					}	
					
					model.addAttribute("equipment", business);
					/**Writing the title, depending if is a new equipment of is an edition**/
					model.addAttribute("title",setTitle(business));
					
					r = operatorService.list(1,user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						operator = ParseJSON.getInstance().getOperatorDTOList(r.getResponse());
					}			
					
					model.addAttribute("operatorList",operator);
					
					return new ModelAndView(folder+"/add");
				}
				else
				{
					model.addAttribute(Constants.errormsg,r.getError().getDesc());
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);

		 return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model, HttpServletRequest request,RedirectAttributes redirect)  {
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.activate(state, id, user.getToken());
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
		else return new ModelAndView(Constants.logoutRedirect);

		if(request.getHeader("Referer").contains("list"))
    		return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirect.addFlashAttribute(Constants.infomsg,"Successfully deleted");
				}
				else
				{
					boolean res = ParseJSON.getInstance().getBoolean(r.getResponse());
					if(!res)
						redirect.addFlashAttribute(Constants.errormsg,"You cannot delete this item");
					else redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
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
}
