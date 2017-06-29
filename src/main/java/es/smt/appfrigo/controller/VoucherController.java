package es.smt.appfrigo.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
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

import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.ImageSet;
import es.smt.appfrigo.bean.NotificationSend;
import es.smt.appfrigo.bean.Retail;
import es.smt.appfrigo.bean.RetailLocation;
import es.smt.appfrigo.bean.Voucher;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.converter.VoucherConverter;
import es.smt.appfrigo.image.ImageManager;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.VoucherDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.service.IPromotionService;
import es.smt.appfrigo.service.IRetailService;
import es.smt.appfrigo.service.IUserAppService;
import es.smt.appfrigo.service.IVoucherService;
import es.smt.appfrigo.utils.ConditionUtil;

@Controller
@RequestMapping("/voucher")
@SessionAttributes(value =  {"retailList"})
public class VoucherController {

	private String folder = "voucher";
	
	@Autowired
	private IVoucherService voucherService;

	@Autowired
	private IRetailService retailService;
	
	@Autowired
	private IUserAppService userAppService;
	
	@Autowired
	private IPromotionService promotionService;
	
	private static ImageSet settings;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<Voucher> result = new ArrayList<Voucher>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = voucherService.list(user.getToken());

				if(r.getError().getCode() == Constants.codeOK)
				{
					result = BeanManager.getInstance().getVoucherList(r.getResponse());
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
		model.addAttribute("voucherList", result);
		
		return new ModelAndView(folder+"/list");
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") int id,Model model, RedirectAttributes redirect) {	
	
//		String error = "Something was wrong";
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = voucherService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute("voucher", BeanManager.getInstance().getVoucher(r.getResponse()));
					
					r = voucherService.getConditiosn(id, user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						model.addAttribute("details", BeanManager.getInstance().getDataList(r.getResponse()));
					}
					
					return new ModelAndView(folder+"/details");
				}
				else
				{
				//	error =  r.getError().getDesc();
					redirect.addFlashAttribute(Constants.errormsg, r.getError().getDesc());
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
		//	error = "Something was wrong"; //Login problem
			redirect.addFlashAttribute(Constants.errormsg, "Something was wrong");
			
		}
//		redirect.addFlashAttribute(Constants.errormsg, error);
		
		return new ModelAndView("redirect:/"+folder+"/list");
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			Voucher v = new Voucher();
			v.setImage(new Image(Constants.defaultImage));
			v.setRetails(new ArrayList<Integer>());
			model.addAttribute("voucher", v);
			
			try {
				ResponseDTO r = retailService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<Retail> result = BeanManager.getInstance().getRetailList(r.getResponse());
					model.addAttribute("retailList",result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/add");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid Voucher voucher, BindingResult bindingResult,ModelMap model) {	
		
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
					VoucherDTO dto = VoucherConverter.getInstance().beanToDto(voucher);
					
					ResponseDTO r = voucherService.add(dto, voucher.getRetails(), user.getToken());
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
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else
			{
				model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
			}
		}
		 
		return new ModelAndView(folder+"/add");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id, Model model) {	
		
		Voucher voucher = new Voucher();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = voucherService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					
					voucher = BeanManager.getInstance().getVoucher(r.getResponse());
					if(voucher.getRetail()!=null && voucher.getRetail().getLocations()!=null && voucher.getRetail().getLocations().size()>0)
					{
						voucher.setRetails( new ArrayList<Integer>());
						for(RetailLocation l :voucher.getRetail().getLocations())
						{
							voucher.getRetails().add(l.getId());
						}
					}
					
					r = retailService.list(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						List<Retail> result = BeanManager.getInstance().getRetailList(r.getResponse());
						model.addAttribute("retailList",result);
					}
					
					model.addAttribute("voucher", voucher);
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
	public ModelAndView edit(@Valid Voucher voucher, BindingResult bindingResult,ModelMap model) {	
		
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
					VoucherDTO dto = VoucherConverter.getInstance().beanToDto(voucher);
					
					ResponseDTO r = voucherService.edit(dto, voucher.getRetails(), user.getToken());
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
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else
			{
				model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
			}
		 }
		 
		 return new ModelAndView(folder+"/edit");
	 }
	
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public ModelAndView activate(@RequestParam("id") int id,Boolean state, Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = voucherService.activate(state, id, user.getToken());
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
	
	@RequestMapping(value = {"/add", "/edit"}, params={"addFile"})
    public ModelAndView addFile(final Voucher voucher, final BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws IOException {
	 
		settings = new ImageSet();
		settings.setLocation(session.getServletContext().getRealPath("/"));
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	if(voucher.getImage()!=null && voucher.getImage().getFile()!=null)
        	{ 
        		settings = ImageManager.getInstance().addImage(voucher.getImage().getFile(), session.getServletContext().getRealPath("/"), session.getServletContext().getContextPath());
        		if(settings!=null)
        		{
        			voucher.getImage().setPath(settings.getPath());
        		}
        		else  model.addAttribute(Constants.errormsg,"Something was wrong");
        	}
        	else  model.addAttribute(Constants.errormsg,"Something was wrong");
        }
        
        model.addAttribute("voucher", voucher);
        model.addAttribute("crop", true);    
        
        if(request.getRequestURI().contains("add"))
        	return new ModelAndView(folder+"/add");
        else return new ModelAndView(folder+"/edit");
	}
	
	@RequestMapping(value = {"/add", "/edit"}, params={"fileDone"})
	public ModelAndView fileDone(final Voucher voucher,  final BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws IOException {
	 
        if (bindingResult.hasErrors()) {
        	model.addAttribute(Constants.errormsg,
    				ErrorManager.getInstance().getBindingResultMessage(bindingResult.getAllErrors()));
        } else {
        	
        	String path = ImageManager.getInstance().imageDone(settings, voucher.getImage(),false);
	        	
	    	if(path!=null)
	    	{
	    		voucher.getImage().setPath(path);
	    	}
	    	else model.addAttribute(Constants.errormsg,"Something was wrong");
	    	
	    	model.addAttribute("crop", false);        	
	    }
	        
        if(request.getRequestURI().contains("add"))
        	return new ModelAndView(folder+"/add");
        else return new ModelAndView(folder+"/edit");
   }
	
	@RequestMapping(value = "/retail/add", method = RequestMethod.GET)
	public ModelAndView addRetail(@RequestParam("id") int id,Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			Voucher v = new Voucher();

			try {
				
				ResponseDTO r = voucherService.get(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					v = BeanManager.getInstance().getVoucher(r.getResponse());
					
					if(v!=null)
					{
						if(v.getRetail()!=null)
						{
							v.setRetails(new ArrayList<Integer>());
							if(v.getRetail().getLocations()!=null && v.getRetail().getLocations().size()>0)
							{
								for(RetailLocation l:v.getRetail().getLocations())
								{
									v.getRetails().add(l.getId());
								}
							}
						}
						
						r = retailService.list(user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							List<Retail> result = BeanManager.getInstance().getRetailList(r.getResponse());
							model.addAttribute("retailList",result);
						}
						else
						{
							model.addAttribute(Constants.errormsg, r.getError().getDesc());
						}
					}
					else model.addAttribute(Constants.errormsg, "The voucher does not exit");
					
				
				}
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			model.addAttribute("voucher", v);
		
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/retail");
	}
	

	@RequestMapping(value = "/retail/add", method = RequestMethod.POST)
	public ModelAndView addRetail(@Valid Voucher voucher, BindingResult bindingResult,ModelMap model) {	
		
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
					
					ResponseDTO r = voucherService.addRetail(voucher.getId(), voucher.getRetails(), user.getToken());
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
		 
		return new ModelAndView(folder+"/retail/add");
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView send(@RequestParam("id") int id,Model model ) {	
		
		NotificationSend ns = new NotificationSend();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				
				ns =  ConditionUtil.getInstance().getConditions(promotionService, user.getToken(),ns);
				
				if(ns!=null)
				{
					ns.setUsers(new ArrayList<Integer>());
					ns.setId(id);	
					if(ns.getListGender()!=null && ns.getListGender().size()>0)
					{
						ns.getListGender().remove(2);
					}
					
					ResponseDTO r = userAppService.getUserList(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						model.addAttribute("users", BeanManager.getInstance().getUserMiniList(r.getResponse()));
						model.addAttribute("ids",new ArrayList<Integer>());
					}
					
					model.addAttribute("voucher", ns);
						
					return new ModelAndView(folder+"/send");
				}
				else model.addAttribute(Constants.errormsg, "Something was wrong");
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("voucher", ns);
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("redirect:/"+folder+"/list");
	 }
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ModelAndView send(@Valid NotificationSend notification, BindingResult bindingResult,ModelMap model) {	
		
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
				if(notification!=null )
				{
					try {
						
						List<String> codes = new ArrayList<String>();
						if(notification.getPostalCode()!=null && !notification.getPostalCode().equals(""))
						{
							codes = Arrays.asList(notification.getPostalCode().split("-"));
						}
						
						if(notification.getSelectedGender()!=null && notification.getSelectedGender().size() == 2)
							notification.setSelectedGender(new ArrayList<Integer>());
						
						ResponseDTO r = voucherService.send(notification.getId(), notification.getSelectedAge(),notification.getSelectedGender(), codes, notification.getUsers(), user.getToken());
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

			}
			else
			{
				model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
			}
		 }
		 
		 return new ModelAndView("redirect:/"+folder+"/edit");
	 }
	
	@RequestMapping(value = "/code/add", method = RequestMethod.GET)
	public ModelAndView addCode(@RequestParam("id") int id,Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
//			Voucher v = new Voucher();

//			try {
				
//				ResponseDTO r = voucherService.get(id, user.getToken());
//				if(r.getError().getCode() == Constants.codeOK)
//				{
//					v = BeanManager.getInstance().getVoucher(r.getResponse());
					
/*					if(v!=null)
					{
						if(v.getRetail()!=null)
						{
							v.setRetails(new ArrayList<Integer>());
							if(v.getRetail().getLocations()!=null && v.getRetail().getLocations().size()>0)
							{
								for(RetailLocation l:v.getRetail().getLocations())
								{
									v.getRetails().add(l.getId());
								}
							}
						}
						
						r = retailService.list(user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							List<Retail> result = BeanManager.getInstance().getRetailList(r.getResponse());
							model.addAttribute("retailList",result);
						}
						else
						{
							model.addAttribute(Constants.errormsg, r.getError().getDesc());
						}
					}
					else model.addAttribute(Constants.errormsg, "The voucher does not exit");*/
					
//				Item i = new Item();
//				i.setId(id);
//				model.addAttribute("voucher", i);
			Image i = new Image();
			i.setId(id);
			model.addAttribute("voucher",i);
//				}
				
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			
			
//			model.addAttribute("voucher", v);
		
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
		}

		return new ModelAndView(folder+"/upload");
	}
	
	
	@RequestMapping(value = "/code/add", method = RequestMethod.POST)
	public ModelAndView addCode(@Valid Image voucher, BindingResult bindingResult,ModelMap model) {	
		
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
				if(voucher!=null )
				{
					try {
					   if (!voucher.getFile().isEmpty()) {
							   
						   File file = new File(session.getServletContext().getRealPath("/") + "/" + voucher.getFile().getName());
						   FileUtils.writeByteArrayToFile(file, voucher.getFile().getBytes());
						   
						   List<String> result = new ArrayList<String>();
						   
						   Scanner scanner = new Scanner(file);
						   while (scanner.hasNextLine()) {
								String line = scanner.nextLine();
								result.add(line);//.append("\n");
						   }
						   ResponseDTO r = voucherService.addVoucherCodes(result, voucher.getId(), user.getToken());
						   if(r.getError().getCode() == Constants.codeOK)
						   {
//							   return new ModelAndView("redirect:/"+folder+"/details?id="+voucher.getId());
								return new ModelAndView("redirect:/"+folder+"/list");
						   }
						   
					   }
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else
			{
				model.addAttribute(Constants.errormsg, Constants.loginError); return new ModelAndView("redirect:/login");
			}
		 }
		 
		 return new ModelAndView("redirect:/"+folder+"/details?id="+voucher.getId());
	 }
	
}