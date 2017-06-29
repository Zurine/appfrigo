package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class SellerService implements ISellerService{

	private String add = "SellerManagement.svc/json/NewSeller";
	private String activate = "SellerManagement.svc/json/ActivateSeller";
	private String get = "SellerManagement.svc/json/GetSeller";
	private String edit = "SellerManagement.svc/json/EditSeller";
	private String allocate = "SellerManagement.svc/json/AllocateSellers";
	private String list =  "SellerManagement.svc/json/ListSellers";
	private String listByOperators =  "SellerManagement.svc/json/ListByOperator";
	private String listByBusiness =  "SellerManagement.svc/json/ListByBusiness";
	private String delete = "SellerManagement.svc/json/Delete";
	private String addList = "SellerManagement.svc/json/ListNewSeller";
	
	
	@Override
	public ResponseDTO list(int state, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"state\":" + state + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
        return r;
	}

	@Override
	public ResponseDTO add(SellerDTO s, TokenDTO t) throws JsonProcessingException, IOException {

		  ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   String request = "{\"sellerdto\":" + new ObjectMapper().writer().writeValueAsString(s)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		   r = RestManager.getInstance().exchangePostWithString(request,   add);
	    	  
		   if (r != null)
		   {
			   r.setError(ErrorManager.getInstance().CheckResult(r));
		   }
		   
		   
		   return r;
	}
	
	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"state\":" + state + ",\"sellerId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
		
	}

	@Override
	public ResponseDTO get(int id, boolean data, TokenDTO t) throws JsonProcessingException, IOException {
		  ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   String request = "{\"sellerId\":" + id+ ",\"data\":" + data
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		   r = RestManager.getInstance().exchangePostWithString(request,   get);
		  	  
			if(r==null)
				r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   return r;

	}

	@Override
	public ResponseDTO edit(SellerDTO d, TokenDTO t) throws JsonProcessingException, IOException {
		  ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   String request = "{\"sellerdto\":" + new ObjectMapper().writer().writeValueAsString(d)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		   r = RestManager.getInstance().exchangePostWithString(request,   edit);
		  	  
			if(r==null)
				r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   return r;
	}

	@Override
	public ResponseDTO allocateSeller(List<SellerDTO> sellers, TokenDTO t)
			throws JsonProcessingException, IOException {
		  ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   
			 String request = "{\"sellers\":" + new ObjectMapper().writer().writeValueAsString(sellers) +
					 ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";


		   r = RestManager.getInstance().exchangePostWithString(request,   allocate);
		  	  
			if(r==null)
				r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   return r;
	}


	@Override
	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException {
		  ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   String request = "{\"sellerId\":" + id
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		   r = RestManager.getInstance().exchangePostWithString(request,   delete);
		  	  
			if(r==null)
				r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   return r;
	}

	@Override
	public ResponseDTO listByOperators(TokenDTO t) throws JsonProcessingException, IOException {
		 ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		   r = RestManager.getInstance().exchangePostWithString(request,   listByOperators);
		  	  
			if(r==null)
				r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   return r;
	}

	@Override
	public ResponseDTO listByBusiness(TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   listByBusiness);
		  	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO addList(List<SellerDTO> d, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		   String request = "{\"sellerdto\":" +  new ObjectMapper().writer().writeValueAsString(d)
		             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   addList);
		  	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}


}
