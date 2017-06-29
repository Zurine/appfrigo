package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Contact;
import es.smt.appfrigo.bean.Distributor;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class DistributorService implements IDistributorService {

	private String add = "DistributorManagement.svc/json/NewDistributor";
	private String get = "DistributorManagement.svc/json/GetDistributor";
	private String edit = "DistributorManagement.svc/json/EditDistributor";
	private String activate = "DistributorManagement.svc/json/ActivateDistributor";
	private String list =  "DistributorManagement.svc/json/ListDistributors";
	private String listByRegion =  "DistributorManagement.svc/json/ListDistributorsByRegion";
	private String delete =  "DistributorManagement.svc/json/Delete";

	@Override
	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException {
         
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
 		
		String request =  "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

		r = RestManager.getInstance().exchangePostWithString(request,   list);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}
	
	@Override
	public ResponseDTO listByRegion(int regionId, TokenDTO t) throws JsonProcessingException, IOException {
         
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
 		
		String request = "{\"regionId\":" + regionId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   listByRegion);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO add(Distributor d, List<Contact> contacs, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		   String request = "{\"distributordto\":" + new ObjectMapper().writer().writeValueAsString(d)
				   + ",\"contacts\":" +  new ObjectMapper().writer().writeValueAsString(contacs) + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		   r = RestManager.getInstance().exchangePostWithString(request,   add);
	    	  
		   if(r==null)
			   r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   
		   
		   return r;
		
	}

	@Override
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"distributorId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO edit(Distributor d, List<Contact> contacs, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"distributordto\":" + new ObjectMapper().writer().writeValueAsString(d)
				 + ",\"contacts\":" +  new ObjectMapper().writer().writeValueAsString(contacs) +  ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
		
	}

	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"state\":" + state + ",\"distributorId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO delete(int distributorId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
 		
		String request = "{\"distributorId\":" + distributorId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   delete);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}


}
