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
public class UserAppService implements IUserAppService{

	private String userApp = "UserManagement.svc/json/ListAppUsers";
	private String userDevice = "UserManagement.svc/json/ListAppUsersWithDevice";
	
	@Override
	public ResponseDTO getUserList(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

    	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
    	 
    	r = RestManager.getInstance().exchangePostWithString(request,   userApp);
    	 
    	if(r==null)
    		 r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
  		
  		return r;
	}

	@Override
	public ResponseDTO getUserDeviceList(TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";
   	 
   	 	r = RestManager.getInstance().exchangePostWithString(request,   userDevice);
    	 
    	if(r==null)
    		 r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
  		
  		return r;
	}

}
