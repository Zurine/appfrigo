package es.smt.appfrigo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Auth;
import es.smt.appfrigo.bean.Dashboard;
import es.smt.appfrigo.bean.Dashboard2;
import es.smt.appfrigo.bean.LoginBean;
import es.smt.appfrigo.bean.Rates;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.constants.PropertiesManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.service.IAuthService;

@Controller
@RequestMapping("/")
public class AuthenticationController {
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private HttpSession session;

	private Logger logger = Logger.getLogger(AuthenticationController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(TimeZone timezone,@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, ModelMap model,  HttpServletRequest request) {	
	
		
//		timezone = TimeZone.getTimeZone("America/Los_Angeles");
		session.setAttribute("timezone", timezone);
		
		if (error != null) {
			model.addAttribute(Constants.errormsg, 
                           getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addAttribute(Constants.errormsg, "You've been logged out successfully.");
		}
		//Otra llamada al dashboard

		try {
			model.addAttribute("url", PropertiesManager.getInstance().getProperty("web.url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("loginBean", new LoginBean());
		return "login";

	 }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login2(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, ModelMap model,  HttpServletRequest request) throws IOException {	
	
		if (error != null) {
			model.addAttribute(Constants.errormsg, 
                           getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addAttribute(Constants.errormsg, "You've been logged out successfully.");
		}
		//Otra llamada al dashboard
		
		model.addAttribute("loginBean", new LoginBean());
		return "login";

	 }
	
	
	
	
	//customize the error message
	private String getErrorMessage(HttpServletRequest request, String key){
		
		Exception exception = 
	                   (Exception) request.getSession().getAttribute(key);
			
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		}else if(exception instanceof LockedException) {
			error = exception.getMessage();
		}else{
			error = "Invalid username and password!";
		}
		return error;
	}
	
	@RequestMapping(value = "/setDefault", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String>  setDefault(@RequestParam("timezone") String id, Model model) {	

		System.out.println("TIMIIING --------------------------------- " +id);
		
//		TimeZone.setDefault();
//		TimeZone timezone
		TimeZone timezone = TimeZone.getTimeZone(id);
//		String[] availId = TimeZone.getAvailableIDs();  
//		String[] timezone = TimeZone.getAvailableIDs(3600000);
	
		
//		String time = id;
		session.setAttribute("timezone", timezone);
		
//		return "home";
		return new ResponseEntity<String>("OK!!!",HttpStatus.NO_CONTENT);

	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home( Model model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!= null)
		{
			
			try{

//				TimeZone.setDefault(timezone);
				

				ResponseDTO r = null;

			
				model.addAttribute("rates", new Rates());
				
				System.out.println("CHECKING THE TIME");
				long A = new Date().getTime();
				if(user.getAuthorities().get(0).getName().equals("ROLE_UNI"))
				{
					Dashboard2 d = new Dashboard2();

					r = authService.getDashboard2(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						d = BeanManager.getInstance().getDashboard2(r.getResponse());

						model.addAttribute("voucherById", d.getVoucherById());
						model.addAttribute("voucherByRetail", d.getVoucherByRetail());
						model.addAttribute("voucherByRange", d.getVoucherByRange());
						model.addAttribute("userByGender", new ObjectMapper().writer().writeValueAsString(d.getUserGender()));
						model.addAttribute("userByStudy", new ObjectMapper().writer().writeValueAsString(d.getUserStudies()));
						model.addAttribute("promotionById", new ObjectMapper().writer().writeValueAsString(d.getPromotionById()));
						model.addAttribute("promotionByType", new ObjectMapper().writer().writeValueAsString(d.getPromotionByType()));
						model.addAttribute("answer1", new ObjectMapper().writer().writeValueAsString(d.getAnswer1()));
						model.addAttribute("answer2", new ObjectMapper().writer().writeValueAsString(d.getAnswer2()));
						model.addAttribute("voucherByGender", d.getVoucherByGender());
						model.addAttribute("voucherByStudy", d.getVoucherByStudy());
						model.addAttribute("voucherByBuyer", d.getVoucherByBuyer());
						model.addAttribute("promotionByGender", d.getPromotionByGender());
						model.addAttribute("promotionByStudy", d.getPromotionByStudy());
						model.addAttribute("promotionByBuyer", d.getPromotionByBuyer());

						model.addAttribute("home", d);

						return "home2";
					}

				}
				else
				{
					long timeA = new Date().getTime();
					Dashboard d = new Dashboard();
					r = authService.getDashboard(user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						long timeA1 = new Date().getTime();
						d = BeanManager.getInstance().getDashboard(r.getResponse());
						Rates rates = BeanManager.getInstance().getRates(r.getResponse());
						long timea2 = new Date().getTime();
						logger.info("Time CONVERTER -> " + (timea2 - timeA1));
						model.addAttribute("home", d);
						model.addAttribute("rates", rates);
						long B = new Date().getTime();
						System.out.println("THE TIME - " + (B-A));
						
					//	model.addAttribute("messages",new ArrayList<MessageMini>());
						
						long timeb = new Date().getTime();
						logger.info("Time total -> " + (timeb - timeA));
						System.out.println("Time total -> " + (timeb - timeA));
						return "home";
					}

				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			model.addAttribute("home",new Dashboard());	
			model.addAttribute("equipmentList", null);
			model.addAttribute("distributor", null);
		}
		else
		{
			model.addAttribute(Constants.errormsg, Constants.loginError); 
			return "redirect:/login";
		}

		
		return "home";

	}
	
	@RequestMapping(value = "/rates", method = RequestMethod.GET) 
	public String rates(@RequestParam("equipmentType") String equipmentType,ModelMap model) {	
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				int id = Integer.parseInt(equipmentType);
				ResponseDTO r = authService.getRates(id,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					Rates rates = BeanManager.getInstance().getRates(r.getResponse());
					model.addAttribute("rates", rates);
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
			return "redirect:/login";
		}
	
		return "/home :: ratesData";
	 }

	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		
		SecurityContextHolder.clearContext();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

	
	@RequestMapping(value = "/newPassword", method = RequestMethod.GET)
	public String newPassword(@RequestParam("id") int id, @RequestParam("code") String code,@RequestParam("lang") String lang,ModelMap model,  HttpServletRequest request, HttpServletResponse response) {
		
		Auth auth = new Auth();
		auth.setUserId(id);
		auth.setCode(code);
		
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	    localeResolver.setLocale(request, response, StringUtils.parseLocaleString(lang));
		
		model.addAttribute("auth",auth);
		
		return "newPassword";
	}
	
	
	@RequestMapping(value = "/newPassword", method = RequestMethod.POST)
	public ModelAndView recover(Auth auth, Model model, RedirectAttributes redirectl,  HttpServletRequest request) {	
		
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale s = localeResolver.resolveLocale(request);
		String  g = s.getLanguage();
		
		if(auth.getPassword().equals(auth.getRepeatPassword())){
		try {
			ResponseDTO r = authService.resetPassword(auth.getUserId(), auth.getCode(), auth.getPassword());
				if(r.getError().getCode() == Constants.codeOK)
				 {
					 if(g.equals("en"))
						 model.addAttribute(Constants.infomsg,"You can now access with your new password");
					 else  model.addAttribute(Constants.infomsg,"Ya puedes acceder con tu nueva contraseña");
					 
					 return new ModelAndView("response");

				 }
				 else {
					 model.addAttribute(Constants.errormsg,r.getError().getDesc());
				 }
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		else {
			if(g.equals("en"))
				 model.addAttribute(Constants.errormsg,"Passwords don't match");
			 else  model.addAttribute(Constants.errormsg,"Las contraseñas no coinciden");
		}
		
		 model.addAttribute("auth",auth);
		
		 return new ModelAndView("newPassword");

		
	}
	
	
	/*@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(Model model) {	
		System.out.println("login");
		model.addAttribute("email", "");
		return "forgotPassword";

	}
	
	@RequestMapping(value = "/requestPassword", method = RequestMethod.POST)
	public ModelAndView requestPassword(String email, ModelMap model,RedirectAttributes redirectAttributes) {	
		
		 if (email != null && !email.equals(""))
		 {
			try {
				ResponseDTO r = authService.requestPassword(email);
				if(r.getError().getCode() == Constants.codeOK)
				{
					redirectAttributes.addFlashAttribute(Constants.errormsg, "We have send you an email");
					
					return new ModelAndView("redirect:/auth/forgotPassword");
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
		else
		{
			redirectAttributes.addFlashAttribute(Constants.errormsg, "Email is required"); 
		}
		 
		return new ModelAndView("redirect:/auth/forgotPassword");
	}*/
}
