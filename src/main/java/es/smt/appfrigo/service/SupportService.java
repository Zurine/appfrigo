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
public class SupportService implements ISupportService{

	private String list =  "SupportManagement.svc/json/List";
	private String get =  "SupportManagement.svc/json/Get";
	
	@Override
	public ResponseDTO list(int state, TokenDTO t) throws IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request = "{\"state\":" + state   + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
   	 	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

	@Override
	public ResponseDTO get(int messageId, TokenDTO t) throws IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		String request = "{\"messageId\":" + messageId   + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   get);
   	 
   	 	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}

}
