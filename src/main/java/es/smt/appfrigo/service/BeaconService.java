package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.BeaconDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class BeaconService implements IBeaconService{

	private String add= "BeaconManagement.svc/json/NewBeacon";
	private String edit= "BeaconManagement.svc/json/editBeacon";
	private String get= "BeaconManagement.svc/json/GetBeacon";
	private String activate= "BeaconManagement.svc/json/Activate";
	private String list = "BeaconManagement.svc/json/listBeacons";
	
	@Override
	public ResponseDTO getList(TokenDTO t) throws JsonProcessingException, IOException {
	
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
        return r;
	}

	@Override
	public ResponseDTO add(BeaconDTO b, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"beacon\":" + new ObjectMapper().writer().writeValueAsString(b)
				+ ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		
		System.out.println("request-> " + request);
		System.out.println("path-> " + add);
		
		r = RestManager.getInstance().exchangePostWithString(request,   add);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}

	@Override
	public ResponseDTO edit(BeaconDTO b, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"beacon\":" + new ObjectMapper().writer().writeValueAsString(b)
				+ ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
		
	}
	

	@Override
	public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"beaconId\":" + id + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		
		r = RestManager.getInstance().exchangePostWithString(request,   get);
	    	  
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		return r;
	}
	

	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"state\":" + state + ",\"id\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
		
	}

}
