package es.smt.appfrigo.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.BusinessF;
import es.smt.appfrigo.bean.BusinessNano;
import es.smt.appfrigo.bean.StatisticSearch;
import es.smt.appfrigo.bean.TrackingDay;
import es.smt.appfrigo.bean.UserMicro;
import es.smt.appfrigo.bean.UserTracking;
import es.smt.appfrigo.bean.UserWorkDay;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SaleMicroDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.ISellerService;
import es.smt.appfrigo.service.IStatisticService;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
	
//	private Logger logger = Logger.getLogger(TrackingController.class);

	private String folder = "tracking";
	
	@Autowired
	private IStatisticService statisticService;
	
	@Autowired
	private ISellerService sellerService;
	
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/productSales", method = RequestMethod.POST)
	public String productSales(@Valid StatisticSearch search, BindingResult bindingResult,ModelMap model ) {	
		
		List<SaleMicroDTO> result = new ArrayList<SaleMicroDTO>();
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
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					session.setAttribute("search", search);
					
					if(search.getStartDate() == null || search.getStartDate().equals("")){
						int year  = Calendar.getInstance().get(Calendar.YEAR);
						search.setStartDate("01/01/"+year);
					}
					
					if(search.getEndDate() == null|| search.getEndDate().equals(""))
						search.setEndDate(formatter.format(new Date()));
					
					ResponseDTO r = statisticService.getProductsSales(search.getId(),search.getGpType(),0,
							formatter.parse(search.getStartDate()+" 00:00"), formatter.parse(search.getEndDate()+" 23:59"), user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						result = ParseJSON.getInstance().getSaleMicroDTOList(r.getResponse());	
						model.addAttribute("products",result);
					}
					else model.addAttribute(Constants.errormsg, r.getError().getDesc());

					
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else return Constants.logoutRedirect;
		}
		
		return folder+"/sales :: productsList";
	}

	@RequestMapping(value = "/productWorkDay", method = RequestMethod.GET)
	public String productWorkDay(@RequestParam("id") int id, ModelMap model ) {	
		
		List<SaleMicroDTO> result = new ArrayList<SaleMicroDTO>();

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
					
				ResponseDTO r = statisticService.getProductsSales(id, 7, 0,new Date(),new Date(), user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getSaleMicroDTOList(r.getResponse());	
					model.addAttribute("products",result);
				}
				else model.addAttribute(Constants.errormsg, r.getError().getDesc());
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 

		}
		else return Constants.logoutRedirect;
		
		return folder+"/tracking :: productsList";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView tracking(ModelMap model) {	
		
		List<BusinessF> sellers = new ArrayList<BusinessF>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = sellerService.listByBusiness(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					sellers = ParseJSON.getInstance().getBusinessFList(r.getResponse());
				}
		
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		UserWorkDay userWorkDay = new UserWorkDay();
		userWorkDay.setUser(new UserMicro());
		userWorkDay.setBusiness(new BusinessNano());
		
		model.addAttribute("sellerList",sellers);
		model.addAttribute("wk",userWorkDay);
		model.addAttribute("search",new StatisticSearch());

		return new ModelAndView(folder+"/tracking");
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String workDay(StatisticSearch search, ModelMap model) {	
		
		UserWorkDay userWorkDay = new UserWorkDay();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {

				if(search!=null){
					
					if(search.getData()!=null && search.getData().size()>0){
						
						if(search.getData().get(0)!=null && !search.getData().get(0).isEmpty()){
							
							String [] d = search.getData().get(0).split("@");
							userWorkDay = getWorkDay(Integer.parseInt(d[0]),Integer.parseInt(d[1]),search,user.getToken());
							
							if(userWorkDay.getCode() == Constants.codeOK){
								model.addAttribute("search",search);
								model.addAttribute("wk",userWorkDay);
							}
							else model.addAttribute(Constants.errormsg, userWorkDay.getMessage());
						}
					}
				}
				else model.addAttribute(Constants.errormsg, Constants.loginError); 
		
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else return Constants.logoutRedirect;
		
		model.addAttribute("search",search);
		
		return folder+"/tracking :: resultsList";
	}
	
	private UserWorkDay getWorkDay(int id, int business, StatisticSearch search, TokenDTO token) throws JsonProcessingException, IOException, ParseException {	
		
		UserWorkDay userWorkDay = new UserWorkDay();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		if(search.getStartDate() == null || search.getStartDate().equals(""))
			search.setStartDate(formatter.format(new Date()));
		if(search.getEndDate() == null|| search.getEndDate().equals(""))
			search.setEndDate(formatter.format(new Date()));
		
		ResponseDTO r = statisticService.getWorkDay(id,business,formatter.parse(search.getStartDate()+" 00:00"),formatter.parse(search.getEndDate()+" 23:59"),token);
		if(r.getError().getCode() == Constants.codeOK)
		{
			userWorkDay = ParseJSON.getInstance().getUserWorkDay(r.getResponse());

			if(userWorkDay.getUser()!=null && userWorkDay.getBusiness()!=null)
				userWorkDay.setCode(Constants.codeOK);
		}
		else{
			userWorkDay.setCode(Constants.codeError);
			userWorkDay.setMessage(r.getError().getDesc());
		}
		
		return userWorkDay;
	}

	@RequestMapping(value = "/getPath", method = RequestMethod.GET)
	public ModelAndView getPath(@RequestParam("id") int id,@RequestParam("userId") int userId,ModelMap model, RedirectAttributes redirectAttributes) {	
		
		UserTracking userWorkDay = new UserTracking();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = statisticService.getPath(id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					userWorkDay = ParseJSON.getInstance().getUserTracking(r.getResponse());

					if(userWorkDay.getTracking()!=null && userWorkDay.getTracking().size()>0)
					{

						int cont = 0;
						for(TrackingDay wd:userWorkDay.getTracking())
						{
							cont++;
							wd.setLabel(cont+"");
//							cont++;
						}
					}
					else model.addAttribute(Constants.errormsg, r.getError().getDesc());
						
					model.addAttribute("seller",userWorkDay.getUser());
					model.addAttribute("business",userWorkDay.getBusiness());
					model.addAttribute("locations",userWorkDay);
					model.addAttribute("locationData",new ObjectMapper().writer().writeValueAsString(userWorkDay.getTracking()));
					
					return new ModelAndView(folder+"/trackingPath");
				}
				else redirectAttributes.addFlashAttribute(Constants.errormsg, r.getError().getDesc()); 
				 
				return new ModelAndView("redirect:/"+folder+"/list?id="+userId);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView("redirect:/"+folder+"/list?id"+userId);
	 }
}
