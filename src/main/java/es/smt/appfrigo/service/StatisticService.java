package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;


@Component
public class StatisticService implements IStatisticService{

	private String sales ="StatsManagement.svc/json/GetStatisticSales";

	private String workDay ="StatsManagement.svc/json/GetWorkDay";
	private String workPath ="StatsManagement.svc/json/GetPath";
	private String workDayRel ="StatsManagement.svc/json/GetRelationWork";
	private String operator ="StatsManagement.svc/json/GetSalesByOperator";
	private String productSales ="StatsManagement.svc/json/GetStatisticProducts";
	private String tickets ="StatsManagement.svc/json/GetTickets";
	private String saleProducts ="StatsManagement.svc/json/GetSaleProducts";
	private String salesMap ="StatsManagement.svc/json/MapSales";
	private String use ="StatsManagement.svc/json/GetUse";
	
	@Override
	public ResponseDTO getSales(List<Integer> business,List<Integer> channel, List<Integer> region, List<Integer> equipType, List<Integer> product, List<Integer> operator, boolean workDay, Date startDate, Date endDate, int type,  TokenDTO t) throws IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		String request = "{\"business\":" + business + ",\"channel\":" + channel + ",\"region\":" + region + ",\"type\":" + equipType + ",\"product\":" + product + ",\"oper\":" + operator  + ",\"gpType\":" + type +
				",\"workDay\":" + workDay + ",\"fechaIni\": " + "\"/Date("+ startDate.getTime()+")/\""+ " ,\"fechaFin\": "  //+0200
		   + "\"/Date("+ endDate.getTime()+")/\""+ ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		   
		r = RestManager.getInstance().exchangePostWithString(request,   sales);
    	  
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
		return r;
	}

	@Override
	public ResponseDTO getWorkDay(int seller,int business,Date startDate, Date endDate, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		String request = "{\"sellerId\":" + seller +",\"businessId\": " + business+ ",\"fechaIni\": " + "\"/Date("+ startDate.getTime()+")/\""+ 
				   " ,\"fechaFin\": "  + "\"/Date("+ endDate.getTime()+")/\"" + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   workDay);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
		return r;
	}


	@Override
	public ResponseDTO getPath(int pathId,TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		String request = "{\"pathId\":" + pathId 
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   workPath);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;

	}

	@Override
	public ResponseDTO getWorkDayRelation(int business, String startDate, String endDate, TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"businessId\":" +business +
				   ",\"fechaIni\": " + new ObjectMapper().writer().writeValueAsString(startDate)+ " ,\"fechaFin\": " 
				   + new ObjectMapper().writer().writeValueAsString(endDate) 
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   workDayRel);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;
	}


	@Override
	public ResponseDTO getSalesByOperator(TokenDTO t) throws JsonProcessingException, IOException {
	ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   operator);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;
	}

	@Override
	public ResponseDTO getProductsSales(int id,int type, int business,  Date startDate, Date endDate,  TokenDTO t)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\": " + id+   ",\"type\": " + type+   ",\"business\": " + business+
				   ",\"fechaIni\": " + "\"/Date("+ startDate.getTime()+")/\""+ " ,\"fechaFin\": " +
				   "\"/Date("+endDate.getTime()+")/\""+ 
	             ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   productSales);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;
	}
	
	@Override
	public ResponseDTO getTickets(List<Integer> business,  Date startDate, Date endDate, TokenDTO t) 
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"business\": " + new ObjectMapper().writer().writeValueAsString(business)+    
				   ",\"fechaIni\": " + "\"/Date("+ startDate.getTime()+")/\""+ " ,\"fechaFin\": " +
				   "\"/Date("+endDate.getTime()+")/\""+ 
	             ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   tickets);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;
	}

	@Override
	public ResponseDTO getSaleProducts(int id, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\": " + id+  

	             ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   saleProducts);
		  
		if (r != null)
		{
		   r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
	   return r;
	}

	@Override
	public ResponseDTO getSalesMap(Date startDate, Date endDate, int operatorId, List<Integer> business,
			List<Integer> product, TokenDTO t) throws IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"business\":" + business   + ",\"products\":" + product + ",\"operatorId\":" + operatorId  + 
			 ",\"start\": " + "\"/Date("+ startDate.getTime()+")/\""+ " ,\"end\": "  //+0200
		   + "\"/Date("+ endDate.getTime()+")/\""+ ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		   
		r = RestManager.getInstance().exchangePostWithString(request,   salesMap);
    	  
		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}
		   
		return r;
	}


	@Override
	public ResponseDTO getUse(TokenDTO t) throws IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   use);

		if (r != null)
		{
			r.setError(ErrorManager.getInstance().CheckResult(r));
		}

		return r;
	}

}
