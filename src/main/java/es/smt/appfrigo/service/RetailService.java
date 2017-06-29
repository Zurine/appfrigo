package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.RetailDTO;
import es.smt.appfrigo.model.RetailLocationDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class RetailService implements IRetailService{

	private String retails =  "RetailManagement.svc/json/List";
	private String add = "RetailManagement.svc/json/New";
	private String get = "RetailManagement.svc/json/Get";
	private String edit = "RetailManagement.svc/json/Edit";
	private String addLocation = "RetailManagement.svc/json/NewLocation";
	private String editLocation = "RetailManagement.svc/json/EditLocation";
	private String getLocation = "RetailManagement.svc/json/GetLocation";
	private String activate = "RetailManagement.svc/json/Activate";
	
	@Override
    public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException
    {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   retails);
   	 
   	 	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
        
   }
	
   @Override
   public ResponseDTO add(RetailDTO c, TokenDTO t) throws JsonProcessingException, IOException
   {
	   ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	
	   String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(c)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
	   r = RestManager.getInstance().exchangePostWithString(request,   add);
    	  
	   if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
   }


	@Override
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		String request = "{\"id\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}


	@Override
	public ResponseDTO edit(RetailDTO c, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(c)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO addLocation(RetailLocationDTO ret, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(ret)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   addLocation);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO editLocation(RetailLocationDTO ret, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(ret)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   editLocation);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO getLocation(int id, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   getLocation);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override  
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {
	ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\":" + id + ",\"state\":" + state
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}


}
