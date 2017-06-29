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
public class ContadorService implements IContadorService{

	private String get = "ContadorManagement.svc/json/GetContadorData";
	
	@Override
	public ResponseDTO getContadorData(int type,String startDate, String endDate, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"type\":" + type + 
				   ",\"fechaIni\": " + new ObjectMapper().writer().writeValueAsString(startDate)+ " ,\"fechaFin\": " 
				   + new ObjectMapper().writer().writeValueAsString(endDate) 
	             + ",\"tokendto\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

}
