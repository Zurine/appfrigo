package es.smt.appfrigo.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.BusinessNano;
import es.smt.appfrigo.bean.StatisticSearch;
import es.smt.appfrigo.bean.TicketDetail;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.file.CSVManager;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SaleMicroDTO;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IStatisticService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
//	private Logger logger = Logger.getLogger(TicketController.class);

	private String folder = "ticket";
	
	@Autowired
	private IStatisticService statisticService;
	
	@Autowired
	private IBusinessService businessService;
	
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView tickets(Model model) {	
		
		List<BusinessNano> equipment = new ArrayList<BusinessNano>();
		StatisticSearch search = new StatisticSearch();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.listEquipment(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					equipment = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
				}
		
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		model.addAttribute("equipmentList",equipment);
		model.addAttribute("search",search);
		
		return new ModelAndView(folder+"/list");
	 }
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String tickets(StatisticSearch search, ModelMap model) {	
		
		List<TicketDetail> result = new ArrayList<TicketDetail>();
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {

				if(search!=null){
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					
					if(search.getStartDate() == null || search.getStartDate().equals("")){
						int year  = Calendar.getInstance().get(Calendar.YEAR);
						search.setStartDate("01/01/"+year);
					}
					if(search.getEndDate() == null|| search.getEndDate().equals(""))
						search.setEndDate(formatter.format(new Date()));
					
					if(search.getEquipment()!=null && search.getEquipment().size()>0){
						ResponseDTO r = statisticService.getTickets(search.getEquipment(),formatter.parse(search.getStartDate()+" 00:00"), formatter.parse(search.getEndDate()+" 23:59"),user.getToken());
						if(r.getError().getCode() == Constants.codeOK)
						{
							result = ParseJSON.getInstance().getTicketDetailList(r.getResponse());
							session.setAttribute("results",result);
							
							model.addAttribute("tickets",result);
						}
						else model.addAttribute(Constants.errormsg, r.getError().getDesc());
					}
					else model.addAttribute(Constants.errormsg, "An equipment must be choosen"); 
					
					
					model.addAttribute("search",search);
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
		session.setAttribute("search",search);
		
		return folder+"/list :: resultsList";
	}
	
	@RequestMapping(value = "/ticketProducts", method = RequestMethod.POST)
	public String ticketProducts(@Valid StatisticSearch search, BindingResult bindingResult,ModelMap model ) {	
		
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
					
					session.setAttribute("search", search);

					ResponseDTO r = statisticService.getSaleProducts(search.getId(), user.getToken());
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
		}
		
		return folder+"/list :: productsList";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id,Boolean state, Model model,RedirectAttributes redirectAttributes, HttpServletRequest request){
		
		List<TicketDetail> tickets = null;
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{		
			try {
				ResponseDTO r = businessService.deleteSale( id, user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					model.addAttribute(Constants.infomsg,"Successfully deleted");
					
					if(session.getAttribute("results")!=null)
					{
						tickets = (List<TicketDetail>)session.getAttribute("results");
						if(tickets!=null){
							for(TicketDetail t : tickets){
								if(t.getSaleId() == id){
									tickets.remove(t);
									break;
								}
									
							}
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
	
		if(session.getAttribute("results")!=null)
		{
			if(tickets!=null)
				model.addAttribute("tickets",tickets);
			else model.addAttribute("tickets", session.getAttribute("results"));
			model.addAttribute("search", session.getAttribute("search"));
			
			return new ModelAndView(folder+"/list");
		}
		else return new ModelAndView("redirect:/"+folder+"/list");
	}

	@RequestMapping(value = "/getTicketCSV", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getTicketCSV( ModelMap model, HttpServletRequest request, HttpServletResponse response) {	
		
		String url =  "";
		if(session.getAttribute("results")!=null)
		{
			@SuppressWarnings("unchecked")
			List<TicketDetail> sales = (List<TicketDetail>)session.getAttribute("results");
			
			List<List<String>> data =  loadTicketTable(sales); 
			
			long name = new Date().getTime();
		//	String url2 = request.getServletContext().getRealPath("/");
			url = CSVManager.getInstance().generateCsvFile(request.getServletContext().getRealPath("/"),data,name);
			
			 response.setContentType("application/xlsx");
		     response.setHeader("Content-Disposition", "attachment; filename="+name+".csv"); 
		}
	 
	    return new FileSystemResource(new File(url));
	}
	
	private List<List<String>> loadTicketTable(List<TicketDetail> list)
	{
		List<List<String>> data = new ArrayList<List<String>>();
		
		List<String> column = new ArrayList<String>();
		column.add("Id");
		column.add("Ticket");
		column.add("Name");
		column.add("Date");
		column.add("Total");
		column.add("Tax Base"); // €
//		column.add("Pieces");
		data.add(column);
		
		List<String> row = null;
		if(list!=null && list.size()>0)
		{
			for(TicketDetail s : list)
			{
				if(!s.isRefunded()){
					row = new ArrayList<String>();
					row.add(s.getId());
					row.add(s.getTicket());
					row.add(s.getName());
					row.add(s.getDate()+"");
					row.add(s.getTotal()+"");
					row.add(s.getTaxBase()+"");
					data.add(row);
				}
			}
		}
		else data.add(new ArrayList<String>());
	
		return data;
	}

}
