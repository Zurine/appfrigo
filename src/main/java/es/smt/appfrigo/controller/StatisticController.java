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

import org.apache.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.BusinessNano;
import es.smt.appfrigo.bean.Channel;
import es.smt.appfrigo.bean.Data;
import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.PDFFile;
import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.SalesStat;
import es.smt.appfrigo.bean.StatisticSearch;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.manager.ErrorManager;
import es.smt.appfrigo.manager.file.CSVManager;
import es.smt.appfrigo.manager.file.PDFManager;
import es.smt.appfrigo.model.BusinessTypeDTO;
import es.smt.appfrigo.model.ProductMiniDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SaleMicroDTO;
import es.smt.appfrigo.rest.BeanManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.service.IBusinessService;
import es.smt.appfrigo.service.IChannelService;
import es.smt.appfrigo.service.IOperatorService;
import es.smt.appfrigo.service.IProductService;
import es.smt.appfrigo.service.IRegionService;
import es.smt.appfrigo.service.IStatisticService;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
	
	private Logger logger = Logger.getLogger(StatisticController.class);

	private String folder = "statistic";
	
	@Autowired
	private IStatisticService statisticService;
	
	@Autowired
	private IBusinessService businessService;
	
	@Autowired
	private IChannelService channelService;
	
	@Autowired
	private IRegionService regionService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IOperatorService operatorService;

	@Autowired
	private HttpSession session;

	
	@RequestMapping(value = "/use", method = RequestMethod.GET)
	public ModelAndView use(Model model) {
		

		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				List<UseInfo> result = new ArrayList<UseInfo>();
				ResponseDTO r = statisticService.getUse(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getUseInfoList(r.getResponse());
				}
				model.addAttribute("resultList",  result);

				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView(folder+"/use");
	 }

	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public ModelAndView list(Model model) {	
		
		List<BusinessNano> equipment = new ArrayList<BusinessNano>();
		List<Channel> channel = new ArrayList<Channel>();
		List<Region> region = new ArrayList<Region>();
		List<BusinessTypeDTO> type = new ArrayList<BusinessTypeDTO>();
		List<ProductMiniDTO> product = new ArrayList<ProductMiniDTO>();
		List<Operator> operator = new ArrayList<Operator>();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				long a = new Date().getTime();
				ResponseDTO r = businessService.listEquipment(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					equipment = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
				}
				r = channelService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					channel = ParseJSON.getInstance().getChannelDTOList(r.getResponse());
				}
				r = regionService.list(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					region = ParseJSON.getInstance().getRegionDTOList(r.getResponse());
				}
				r = businessService.listBusinessType(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					type = ParseJSON.getInstance().getBusinessTypeDTOList(r.getResponse());
				}
				r = productService.list(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					product = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
				}
				r = operatorService.list(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					operator = ParseJSON.getInstance().getOperatorDTOList(r.getResponse());
				}
				long b = new Date().getTime();
				logger.info("Time select -> " + (b - a));
				
				//Load First Data
				r = statisticService.getSalesByOperator(user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					List<SalesStat> result  = BeanManager.getInstance().getSales(r.getResponse());	

					session.setAttribute("results",result);
					model.addAttribute("salesList",result);
					model.addAttribute("salesData",new ObjectMapper().writer().writeValueAsString(result));
				}
				long c = new Date().getTime();
				logger.info("Time statist -> " + (c -b));
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			model.addAttribute("equipmentList",  equipment);
			model.addAttribute("channelList",  channel);
			model.addAttribute("typeList",  type);
			model.addAttribute("regionList",  region);
			model.addAttribute("productList",  product);
			model.addAttribute("url",  "");
			model.addAttribute("operatorList",  operator);
			
			StatisticSearch ss = new StatisticSearch();
			ss.setGpType(7);
			ss.setStartDate("01/01/"+Calendar.getInstance().get(Calendar.YEAR));
			String day =  Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"";
			if(day.length() == 1)
				day = "0"+day;
			String month = Calendar.getInstance().get(Calendar.MONTH)+1 +"";
			if(month.length() == 1)
				month = "0"+month;
			ss.setEndDate(day+"/"+month+"/"+Calendar.getInstance().get(Calendar.YEAR));
			model.addAttribute("type",ss.getGpType());
			model.addAttribute("search", ss);
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView(folder+"/sales");
	 }
	
	@RequestMapping(value = "/sales", method = RequestMethod.POST)
	public String search(@Valid StatisticSearch search, BindingResult bindingResult,ModelMap model ) {	
		
		List<SalesStat> result = new ArrayList<SalesStat>();
		SalesStat total = new SalesStat();
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
					
					ResponseDTO r = statisticService.getSales(search.getEquipment(),search.getChannel(), search.getRegion(), search.getEquipmentType(),search.getProduct(),
						search.getOperator(), search.isWorkDay(), formatter.parse(search.getStartDate()+" 00:00"),formatter.parse(search.getEndDate()+" 23:59"),search.getGpType(), user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						result = BeanManager.getInstance().getSales(r.getResponse());	
						if(result!=null && result.size()>0)
						{
							result.get(0).setType(search.getGpType());
						}

						total.setTotal(result.stream().mapToDouble(o -> o.getTotal()).sum());

						session.setAttribute("results",result);
						session.setAttribute("search", search);
						model.addAttribute("salesData",new ObjectMapper().writer().writeValueAsString(result)); 
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
			
			model.addAttribute("type",search.getGpType());
			model.addAttribute("total",total);
			model.addAttribute("salesList",result);
		}
		
		return folder+"/sales :: resultsList";
	}
	
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
	
	@RequestMapping(value = "/salesMap", method = RequestMethod.GET)
	public ModelAndView salesMap(@RequestParam("operator") int operator,@RequestParam("business") int business, Model model) {	
		
		List<BusinessNano> equipment = new ArrayList<BusinessNano>();
		List<ProductMiniDTO> product = new ArrayList<ProductMiniDTO>();
		MapSearch mapSearch = new MapSearch();
		List<MapSales> result = new ArrayList<MapSales>();
		
		UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && user.isCredentialsNonExpired())
		{
			try {
				ResponseDTO r = businessService.listEquipment(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					equipment = ParseJSON.getInstance().getBusinessNanoList(r.getResponse());
				}
				r = productService.list(Constants.all,user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					product = ParseJSON.getInstance().getProductMiniDTOList(r.getResponse());
				}
				long b = new Date().getTime();
				
				StatisticSearch search = (StatisticSearch)session.getAttribute("search");
				Date start = null;
				Date end = null;
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				
				if(search==null){
					search = new StatisticSearch();
				}
				if(search.getEquipment() == null)
					search.setEquipment(new ArrayList<Integer>());
				if(search.getProduct() == null)
					search.setProduct(new ArrayList<Integer>());
				
				
				if(business!=0)
				{
					search.setEquipment(new ArrayList<Integer>());
					search.setProduct(new ArrayList<Integer>());
					search.getEquipment().add(business);
					search.setStartDate(null);
					search.setEndDate(null);
				}
				
				
				if(search.getStartDate() == null ){
					
					Calendar cal1 = Calendar.getInstance();
					
					start = DateUtils.setMonths(new Date(),cal1.get(Calendar.MONTH));
					start = DateUtils.setDays(start, 1);
					start = DateUtils.setHours(start,0);
					start = DateUtils.setMinutes(start, 0);
					search.setStartDate(formatter.format(start));
				}
				else start = formatter.parse(search.getStartDate());
				
				if(search.getEndDate() == null){
					end = DateUtils.setHours(new Date(),23);
					end = DateUtils.setMinutes(end, 59);
					search.setEndDate(formatter.format(end));
				}
				else {
					end = formatter.parse(search.getEndDate());
					end = DateUtils.setHours(end,23);
					end = DateUtils.setMinutes(end, 59);
				}


				//Load First Data
				r = statisticService.getSalesMap(start,end,operator, search.getEquipment(), search.getProduct(),user.getToken());
				if(r.getError().getCode() == Constants.codeOK)
				{
					result = ParseJSON.getInstance().getMapSalesList(r.getResponse());
					model.addAttribute("salesMap", new ObjectMapper().writer().writeValueAsString(result));
				}
				
				mapSearch.setStartDate(search.getStartDate());
				mapSearch.setEndDate(search.getEndDate());
				mapSearch.setEquipment(search.getEquipment());
				mapSearch.setProduct(search.getProduct());
				
				
				
				long c = new Date().getTime();
				logger.info("Time statist -> " + (c -b));
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 

			model.addAttribute("search", mapSearch);
			model.addAttribute("equipmentList",  equipment);
			model.addAttribute("productList",  product);
			
		}
		else return new ModelAndView(Constants.logoutRedirect);
		
		return new ModelAndView(folder+"/salesMap");
	}
	

	@RequestMapping(value = "/salesMap", method = RequestMethod.POST)
	public String salesMap(@Valid MapSearch search, BindingResult bindingResult,ModelMap model ) {	
		
		
		List<MapSales> result = new ArrayList<MapSales>();
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
					
					Date start = null;
					Date end = null;
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

					start = formatter.parse(search.getStartDate()+" 00:00");
					end = formatter.parse(search.getEndDate()+" 23:59");
					if(search.getProduct()==null)
						search.setProduct(new ArrayList<Integer>());
					if(search.getEquipment()==null)
						search.setEquipment(new ArrayList<Integer>());
					
					//Load First Data
					ResponseDTO r = statisticService.getSalesMap(start,end, 0, search.getEquipment(), search.getProduct(),user.getToken());
					if(r.getError().getCode() == Constants.codeOK)
					{
						result  = ParseJSON.getInstance().getMapSalesList(r.getResponse());
						model.addAttribute("salesMap", new ObjectMapper().writer().writeValueAsString(result));
					}
					
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
		
		return folder+"/salesMap :: resultsList";
		
		
	}
	
	@RequestMapping(value = "/getSalesCSV", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getSalesCSV(ModelMap model, HttpServletRequest request, HttpServletResponse response) {	
		
		String url =  "";
		if(session.getAttribute("results")!=null)
		{
			@SuppressWarnings("unchecked")
			List<SalesStat> sales = (List<SalesStat>)session.getAttribute("results");
			
			List<List<String>> data =  loadSalesTable(sales); 
			
			long name = new Date().getTime();
			url = CSVManager.getInstance().generateCsvFile(request.getServletContext().getRealPath("/"),data,name);
			 
			 response.setContentType("application/xlsx");
		     response.setHeader("Content-Disposition", "attachment; filename="+name+".csv"); 
		}
		

	    return new FileSystemResource(new File(url));
	}
	
	private List<List<String>> loadSalesTable(List<SalesStat> list)
	{
		List<List<String>> data = new ArrayList<List<String>>();
		
		List<String> column = new ArrayList<String>();
		if(list.get(0).getType() == 0)
			column.add("Seller");
		else column.add("Business");
		column.add("Total");
		column.add("T. Gifts");
		column.add("T. Work Day");
		column.add("Daily Unit.");
		column.add("Daily"); // €
		column.add("Daily Hour Av.");
		column.add("T. People");
		column.add("Pick Up R.");
		data.add(column);
		
		List<String> row = null;
		if(list!=null && list.size()>0)
		{
			for(SalesStat s : list)
			{
				row = new ArrayList<String>();
				row.add(s.getItem().getName()+"");
				row.add(s.getTotal()+"");
				row.add(s.getTotalGifs()+"");
				row.add(s.getWorkDay()+"");
				row.add(s.getDailyAmount()+"");
				row.add(s.getDailyTotal()+"");
				row.add(s.getDailyHours()+"");
				row.add(s.getTraffic()+"");
				row.add(s.getPickUpRate()+"");
				data.add(row);
			}
		}
		else data.add(new ArrayList<String>());
	
		return data;
	}
	
	private PDFFile loadSalesPdfData(List<SalesStat> list)
	{
		int page1= 33;
		int page2 = 43;
		
		PDFFile file = new PDFFile();

		List<String> column = new ArrayList<String>();
		if(list.get(0).getType() == 0)
			column.add("Seller");
		else column.add("Business");
		column.add("Total");
		column.add("T. Gifts");
		column.add("T. Work Day");
		column.add("Daily Unit.");
		column.add("Daily");
		column.add("Daily H. Av.");
		column.add("T. People");
		column.add("Pick Up Rate");
		file.setHeader(column);
		
		
		List<List<String>> data = new ArrayList<List<String>>();
		file.setTable(new ArrayList<List<List<String>>>());
		List<String> row = null;
		int i = 0;
		int pageRow = page1;
		if(list!=null && list.size()>0)
		{
			for(SalesStat s : list)
			{
				row = new ArrayList<String>();
				if(s.getItem().getName().length() > 17 )
					row.add(s.getItem().getName().substring(0, 16));
				else row.add(s.getItem().getName());
				row.add(s.getTotal()+"");
				row.add(s.getTotalGifs()+"");
				row.add(s.getWorkDay()+"");
				row.add(s.getDailyAmount()+"");
				row.add(s.getDailyTotal()+"");
				row.add(s.getDailyHours()+"");
				row.add(s.getTraffic()+"");
				row.add(s.getPickUpRate()+"");
				data.add(row);
				if(i==pageRow)
				{
					file.getTable().add(data);
					data = new ArrayList<List<String>>();
					i = 0;
					pageRow = page2;
				}
				i++;
			}
		}
		else data.add(new ArrayList<String>());
		
		file.getTable().add(data);
		
		return file;
	}
	
	@RequestMapping(value = "/getSalesPDF", method = RequestMethod.POST)
	public String getSalesPDF(StatisticSearch search,ModelMap model, HttpServletRequest request, HttpServletResponse response)  {	
		
		String url =  "";
		int maxSize = 0;
		if(session.getAttribute("results")!=null)
		{
			@SuppressWarnings("unchecked")
			List<SalesStat> sales = (List<SalesStat>)session.getAttribute("results");
			
			UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			try{
				search = (StatisticSearch)session.getAttribute("search");
				List<Data> data2 = new ArrayList<Data>();
				
				if(search!=null)
				{
					List<String> text = new ArrayList<String>();
					ResponseDTO r =  null;
					
					if(search.getChannel()!=null)
					{
						text = new ArrayList<String>();
						for(Integer i:search.getChannel())
						{
							r =  channelService.get(i, user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								text.add(ParseJSON.getInstance().getChannelDTO(r.getResponse()).getName());
								if(text.toString().length()> maxSize)
									maxSize = text.toString().length();
							}
						}
						data2.add(new Data("Channel: ", text.toString().replace("[", "").replace("]", "")));
					}
					else data2.add(new Data("Channel: ", "-"));
					
					if(search.getEquipment()!=null)
					{
						text = new ArrayList<String>();
						for(Integer i:search.getEquipment())
						{
							r =  businessService.get(i, false,user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								text.add(ParseJSON.getInstance().getBusinessDTO(r.getResponse()).getName());
								if(text.toString().length()> maxSize)
									maxSize = text.toString().length();
							}
						}
						data2.add(new Data("Equipment: ", text.toString().replace("[", "").replace("]", "")));
					}
					else data2.add(new Data("Equipment: ", "-"));
					
					if(search.getEquipmentType()!=null)
					{
						text = new ArrayList<String>();
						for(Integer i:search.getEquipmentType())
						{
							r =  businessService.getBusinessType(i, user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								text.add(ParseJSON.getInstance().getBusinessTypeDTO(r.getResponse()).getName());
								if(text.toString().length()> maxSize)
									maxSize = text.toString().length();
							}
						}
						data2.add(new Data("Equipment Type: ", text.toString().replace("[", "").replace("]", "")));
					}
					else data2.add(new Data("Equipment Type: ", "-"));
					
					if(search.getProduct()!=null)
					{
						text = new ArrayList<String>();
						for(Integer i:search.getEquipmentType())
						{
							r =  productService.get(i,false , user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								text.add(ParseJSON.getInstance().getProductDTO(r.getResponse()).getDescription());
								if(text.toString().length()> maxSize)
									maxSize = text.toString().length();
							}
						}
						data2.add(new Data("Product: ", text.toString().replace("[", "").replace("]", "")));
					}
					else data2.add(new Data("Product: ", "-"));
					
					if(search.getRegion()!=null)
					{
						text = new ArrayList<String>();
						for(Integer i:search.getRegion())
						{
							r =  regionService.get(i, user.getToken());
							if(r.getError().getCode() == Constants.codeOK)
							{
								text.add(ParseJSON.getInstance().getRegionDTO(r.getResponse()).getDescription());
								if(text.toString().length()> maxSize)
									maxSize = text.toString().length();
							}
						}
						data2.add(new Data("Region: ", text.toString().replace("[", "").replace("]", "")));
					}
					else data2.add(new Data("Region: ", "-"));
				}
			
				PDFFile file = loadSalesPdfData(sales);
				file.setFilter(data2);
				file.setTitle("Sales");
				if(search!=null)
				{
					file.setStartDate(search.getStartDate());
					file.setEndDate(search.getEndDate());
				}
		
				file.setMaxSize(maxSize);
				file.setName(new Date().getTime());
				file.setPath(request.getServletContext().getRealPath("/"));
			
				List<Double> colSize = new ArrayList<Double>();
				//9Columns
				colSize.add(0.2);
				colSize.add(0.1);
				colSize.add(0.1);
				colSize.add(0.1);
				colSize.add(0.1);
				colSize.add(0.1);
				colSize.add(0.1);
				colSize.add(0.1);
				colSize.add(0.1);
				file.setCols(colSize);
				url = PDFManager.getInstance().generatePDFFile(file);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (COSVisitorException e) {
				e.printStackTrace();
			}
		}
		
		String scheme = request.getScheme();
		String host = request.getHeader("Host");        // includes server name and server port
		String contextPath = request.getContextPath();  // includes leading forward slash

		String resultPath = scheme + "://" + host + contextPath;
		url =resultPath+"/container/"+url;
		model.addAttribute("url",url);
		
		return folder+"/sales :: file-fragment";
	}
}
