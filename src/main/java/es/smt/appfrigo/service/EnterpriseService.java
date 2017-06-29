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
public class EnterpriseService implements IEnterpriseService{
	
	private String listEnterprise = "EnterpriseManagement.svc/json/ListEnterprise";
	private String listEnterpriseUser = "EnterpriseManagement.svc/json/ListEnterpriseUser";

	@Override
    public ResponseDTO getEnterpriseList(TokenDTO t) throws JsonProcessingException, IOException
    {	
    	ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   listEnterprise);
  	  
  	  	if (r != null)
  	  		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
        
    }

	@Override
	public ResponseDTO listEnterprises(TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

	  	String request= "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
	    	
	  	r = RestManager.getInstance().exchangePostWithString(request,   listEnterpriseUser);
	  	  
	  	if (r != null)
	  		r.setError(ErrorManager.getInstance().CheckResult(r));
		   
        return r;
	}

}
