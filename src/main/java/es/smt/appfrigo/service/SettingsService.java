package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class SettingsService implements ISettingsService{

	private String setInactiveTime = "EquipmentManagement.svc/json/SetInactiveTime";
	private String getInactiveTime = "EquipmentManagement.svc/json/GetInactiveTime";
	private String setOfferTime = "BusinessManagement.svc/json/SetOfferTime";
	private String getOfferTime = "BusinessManagement.svc/json/GetOfferTime";
	
	@Override
	public ResponseDTO getInativeTime(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   getInactiveTime);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO setInativeTime(int value, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request = "{\"value\":" + value + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   setInactiveTime);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO getOfferTime(TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   getOfferTime);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO setOfferTime(int value, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request = "{\"value\":" + value + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   setOfferTime);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

}
