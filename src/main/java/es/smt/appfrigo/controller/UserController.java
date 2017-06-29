package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.Registration;
import es.smt.appfrigo.bean.StatisticsMini;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.EquipmentManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.UserAdminDTO;
import es.smt.appfrigo.model.UserMiniDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IOperatorService;
import es.smt.appfrigo.service.IUserService;
import es.smt.appfrigo.utils.ListManager;

@Controller
@RequestMapping("/user")
@SessionAttributes(value =  {"list","types"})
public class UserController {

	private String folder = "user";
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IOperatorService operatorService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("state") int state,Model model ) {	
		
		List<UserAdminDTO> result = new ArrayList<UserAdminDTO>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			
			try {
				ResponseDTO r = userService.list(state,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getUserAdminDTOList(r.getResponse());
					if(result!=null)
					{
						Map<Integer,String> types = ListManager.getInstance().getUserType();
						for (UserAdminDTO userAdminDTO : result) {
							if(types.get(userAdminDTO.getType())!=null)
								userAdminDTO.setTypeDesc(types.get(userAdminDTO.getType()));
						}
					}
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
		model.addAttribute("userList", result);
		
		return new ModelAndView(folder+"/list");
	 }

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			UserAdminDTO useradmin = new UserAdminDTO();
			try {
				model.addAttribute("types",ListManager.getInstance().getUserType());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("user", useradmin);
			
			/**Writing the title, depending if is a new user of is an edition**/
			model.addAttribute("title",	setTitle(useradmin));
		}
		else return new ModelAndView(Constants.logoutRedirect);

		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value="/getBusiness")  //, params={"getCombo"}
	public String getBusiness(final UserAdminDTO useradmin, final BindingResult bindingResult,Model model, HttpServletRequest request) {
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{	
			try {
				
				Map<Integer,String> userType = ListManager.getInstance().getUserType();
				useradmin.setBusinessIds(new ArrayList<Integer>());
				if(userType!=null && useradmin.getType()!= 0)
				{
					if(userType.get(useradmin.getType()).equalsIgnoreCase("Operator"))
					{
						ResponseDTO r = operatorService.list(1,user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							List<Operator> list = ParseJSON.getInstance().getOperatorDTOList(r.getResponse());
							model.addAttribute("list", list);
						}
					}
					else if(userType.get(useradmin.getType()).equalsIgnoreCase("Supervisor") || userType.get(useradmin.getType()).equalsIgnoreCase("MSM"))
					{
						ResponseDTO r = businessService.listMini(user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							List<BusinessMiniDTO> list = EquipmentManager.getInstance().getActive(ParseJSON.getInstance().getBusinessMiniDTOList(r.getResponse()));
							model.addAttribute("list", list);
						}
					}
				}
				model.addAttribute("user", useradmin);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return folder+"/add :: fragment-business";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid UserAdminDTO user, BindingResult bindingResult,ModelMap model) {	
		
		 if (bindingResult.hasErrors()) 
		 {
			model.addAttribute(Constants.errormsg,
					ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors())); 
		 }
		 else
		 {
			 if(user!=null)
			 {
				 UserAuth userAuth = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				 if(userAuth!=null && userAuth.isCredentialsNonExpired())
				 {
					 try {
						 Map<Integer,String> types = ListManager.getInstance().getUserType();
						 
						 /**New User **/
						 if(user.getId() == 0){
						 
							 if(user.getPassword()!=null && !user.getPassword().equals("") && user.getPassword().equals(user.getPasswordRep()))
							 {
							 
								 if(!types.get(user.getType()).equalsIgnoreCase("SoyFrigo Admin"))
								 {
									 if(user.getBusinessIds()==null || user.getBusinessIds().size()==0)
									 {
										 model.addAttribute(Constants.errormsg, "Equipment is necessary");
										 
										 model.addAttribute("user", user);
										 
										 return new ModelAndView(folder+"/add");
									 }
						 		 }
								 user.setActive(true);
								 user.setEnterpriseId(userAuth.getToken().getEnterpriseId());
							
								 ResponseDTO r = userService.add(user,user.getBusinessIds(), userAuth.getToken());
								 if(r.getError().getCode() == Constants.codeOK)
								 {
									return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
								 }
								 else model.addAttribute(Constants.errormsg, r.getError().getDesc());
							 }
							 else model.addAttribute(Constants.errormsg, "Password does not match");
						 }
						 /**Edit User **/
						 else{
							if(user!=null )
							{
								if(!types.get(user.getType()).equalsIgnoreCase("SoyFrigo Admin"))
								{
									if(  user.getBusinessIds()!=null && user.getBusinessIds().size()>0){
										List<Default> mini = new ArrayList<Default>();
										for(Integer i:user.getBusinessIds())
										{
											mini.add(new Default(i));
										}
										user.setItems(mini);
									}
									else model.addAttribute(Constants.errormsg, "Equipment is necessary");
								}

								ResponseDTO r = userService.edit(user, userAuth.getToken());
								if(r.getError().getCode() == Constants.codeOK)
								{
									return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
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
				 else return new ModelAndView(Constants.logoutRedirect);
			 }
		 }
		 
		 /**Writing the title, depending if is a new user of is an edition**/
		 model.addAttribute("title",	setTitle(user));
		 model.addAttribute("user", user);
		 
		return new ModelAndView(folder+"/add");
	}

	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") String id,Model model) {	
		UserAuth userAuth = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(userAuth!=null && userAuth.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = userService.get(Integer.parseInt(id), userAuth.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					Map<Integer,String> types = ListManager.getInstance().getUserType();
					UserAdminDTO user = ParseJSON.getInstance().getUserAdminDTO(r.getResponse());
					if(types.get(user.getType())!=null)
						user.setTypeDesc(types.get(user.getType()));
					user.setData(new StatisticsMini());
					if(user.getItems()!=null)
						user.getData().setEquipments(user.getItems().size());

					model.addAttribute("user", user);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ModelAndView(folder+"/details");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model ) {	
		
		UserAuth  user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if( user!=null &&  user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = userService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					UserAdminDTO useradmin =  ParseJSON.getInstance().getUserAdminDTO(r.getResponse());

					Map<Integer,String> types = ListManager.getInstance().getUserType();
					model.addAttribute("types",types);
					if(useradmin!=null && useradmin.getItems()!=null && useradmin.getItems().size()>0)
					{
						if(types.get(useradmin.getType()).equalsIgnoreCase("Operator") )
						{
							r = operatorService.list(1,user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								List<Operator> list = ParseJSON.getInstance().getOperatorDTOList(r.getResponse());
								model.addAttribute("list", list);
							}
							
							/**Es lo mismo sea operator o no**/
							List<Integer> ids = new ArrayList<Integer>();
							if(useradmin.getItems()!=null)
							{
								for(Default b:useradmin.getItems())
								{
									ids.add(b.getId());
								}
								useradmin.setBusinessIds(ids);
							}
						}
						else if(types.get(useradmin.getType()).equalsIgnoreCase("Supervisor") || types.get(useradmin.getType()).equalsIgnoreCase("MSM"))
						{
					
							r = businessService.listMini(user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								List<BusinessMiniDTO> list = EquipmentManager.getInstance().getActive(ParseJSON.getInstance().getBusinessMiniDTOList(r.getResponse()));
								model.addAttribute("list", list);
							}
							
							List<Integer> ids = new ArrayList<Integer>();
							if(useradmin.getItems()!=null)
							{
								for(Default b:useradmin.getItems())
								{
									ids.add(b.getId());
								}
								useradmin.setBusinessIds(ids);
							}
							
						}
					}
					model.addAttribute("user", useradmin);
					
					/**Writing the title, depending if is a new user of is an edition**/
					model.addAttribute("title",	setTitle(useradmin));
					
					return new ModelAndView(folder+"/add");
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
		else return new ModelAndView(Constants.logoutRedirect);

		 return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = userService.activate(state, id, user.getToken());
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
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirect, HttpServletRequest request){
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = userService.delete( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
					redirect.addFlashAttribute(Constants.infomsg,"Successfully deleted");
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

		if(request.getRequestURI().contains("list"))
			return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	    else return new ModelAndView("redirect:/"+folder+"/get?id="+id);
	}
	
	@RequestMapping(value = {"/editSellerPass","/editUserPass"}, method = RequestMethod.GET)
	public ModelAndView editPassword(@RequestParam("id") int id, Model model, HttpServletRequest request) {	
		
		UserAdminDTO u = new UserAdminDTO();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = userService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					u = ParseJSON.getInstance().getUserAdminDTO(r.getResponse());
					
					Registration reg = new Registration();
					reg.setId(u.getId());
					reg.setNickName(u.getName()+" " + u.getSurname());
				
					model.addAttribute("seller", reg);
					
					if(request.getRequestURI().contains("editSellerPass"))
			        	return new ModelAndView("seller/editPassword");
			        else return new ModelAndView(folder+"/editPassword");
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
		else return new ModelAndView(Constants.logoutRedirect);
	
		if(request.getRequestURI().contains("editSellerPass"))
        	return new ModelAndView("redirect:/seller/list?state="+Constants.active);
        else return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	 }
	
	@RequestMapping(value = {"/editSellerPass","/editUserPass"}, method = RequestMethod.POST)
	public ModelAndView editPassword(@Valid Registration seller, BindingResult bindingResult,ModelMap model, HttpServletRequest request) {	
		
		 UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if(user!=null && user.isCredentialsNonExpired())
		 {
			 if(seller!=null && seller.getPassword()!=null && seller.getRepPassword()!=null && seller.getPassword().equals(seller.getRepPassword()))
			 { 
				 SellerDTO dto = new SellerDTO();
				 dto.setId(seller.getId());
				 
				 UserMiniDTO um = new UserMiniDTO();
				 um.setPassword(seller.getPassword());
				 dto.setUser(um);
		
				try {
					ResponseDTO r = userService.editPassword(dto, user.getToken());
					if(r.getError().getCode() != Constants.codeOK)
					{
						model.addAttribute(Constants.errormsg, r.getError().getDesc());
					}
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 else  model.addAttribute(Constants.errormsg,"Passwords don't match"); 
		}
		else return new ModelAndView(Constants.logoutRedirect);
	 
		if(request.getRequestURI().contains("editSellerPass"))
        	return new ModelAndView("redirect:/seller/list?state="+Constants.active);
        else return new ModelAndView("redirect:/"+folder+"/list?state="+Constants.active);
	 }
	
	private String setTitle(UserAdminDTO u){
		String title = "";
		if(u.getId() == 0)
			title = "Create New User";
		else title = "Edit " + u.getName() + " " + u.getSurname();
		
		return title;
	}
}
