package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.model.VoucherDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class VoucherService implements IVoucherService {

	
	private String list =  "VoucherManagement.svc/json/List";
	private String add = "VoucherManagement.svc/json/New";
	private String get = "VoucherManagement.svc/json/Get";
	private String edit = "VoucherManagement.svc/json/Edit";
	private String activate = "VoucherManagement.svc/json/Activate";
	private String addRetail = "VoucherManagement.svc/json/AddRetail";
	private String send = "VoucherManagement.svc/json/AddUsers";
	private String getConditions = "VoucherManagement.svc/json/GetVoucherConditions";
	private String addVoucherCode = "VoucherManagement.svc/json/AddVoucherCodes";
	
	@Override
	public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
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

	@Override
	public ResponseDTO add(VoucherDTO v,List<Integer> retails,  TokenDTO t) throws JsonProcessingException, IOException {
		  ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		   String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(v)+ ",\"retails\":" + new ObjectMapper().writer().writeValueAsString(retails)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		
		   r = RestManager.getInstance().exchangePostWithString(request,   add);
	    	  
		   if(r==null)
			   r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));

		   return r;
	}

	@Override
	public ResponseDTO edit(VoucherDTO v, List<Integer> retails, TokenDTO t) throws JsonProcessingException, IOException {
	ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(v)+ ",\"retails\":" + new ObjectMapper().writer().writeValueAsString(retails)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO addRetail(int id, List<Integer> retails, TokenDTO t)	throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\":" + id + ",\"retails\":" + new ObjectMapper().writer().writeValueAsString(retails)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   addRetail);
	    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO send(int voucherId, List<Integer> age, List<Integer> gender, List<String> cp, List<Integer> users, TokenDTO t)
			throws JsonProcessingException, IOException {
	ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"voucherId\":" + voucherId + ",\"age\":" + new ObjectMapper().writer().writeValueAsString(age) 
				+ ",\"gender\":" + new ObjectMapper().writer().writeValueAsString(gender)
				+ ",\"cp\":" + new ObjectMapper().writer().writeValueAsString(cp)+ ",\"users\":" + new ObjectMapper().writer().writeValueAsString(users)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   send);
	    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

		return r;
	}

	@Override
	public ResponseDTO getConditiosn(int id, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"id\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   getConditions);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO addVoucherCodes(List<String> codes, int voucherId, TokenDTO t)
			throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"codes\":" + new ObjectMapper().writer().writeValueAsString(codes)
				+ ",\"voucherId\":" +voucherId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   addVoucherCode);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

}
