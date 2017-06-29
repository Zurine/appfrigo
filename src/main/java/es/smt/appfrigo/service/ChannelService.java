package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Channel;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class ChannelService implements IChannelService {

	private String channels =  "ChannelManagement.svc/json/List";
	private String add = "ChannelManagement.svc/json/New";
	private String get = "ChannelManagement.svc/json/Get";
	private String edit = "ChannelManagement.svc/json/Edit";
	private String activate = "ChannelManagement.svc/json/Activate";
	private String delete = "ChannelManagement.svc/json/Delete";
	
	@Override

    public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException
    {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   channels);
   	 
   	 	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
        
   }
	
	@Override
   public ResponseDTO add(Channel u, TokenDTO t) throws JsonProcessingException, IOException
   {
	   ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	
	   String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(u)
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
	public ResponseDTO edit(Channel u, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(u)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   edit);
    	  
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

	@Override
	public ResponseDTO delete(int id, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"channelId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   delete);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		 return r;
	}

}
