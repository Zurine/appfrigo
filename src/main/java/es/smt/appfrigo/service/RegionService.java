package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class RegionService implements IRegionService {

	private String add = "RegionManagement.svc/json/NewRegion";
	private String get = "RegionManagement.svc/json/GetRegion";
	private String edit = "RegionManagement.svc/json/EditRegion";
	private String activate = "RegionManagement.svc/json/ActivateRegion";
	private String delete = "RegionManagement.svc/json/Delete";
	private String list =  "RegionManagement.svc/json/ListRegions";
	
	@Override

    public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException
    {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 r = RestManager.getInstance().exchangePostWithString(request,   list);
   	 
        return r;
        
    }
	
	@Override
   public ResponseDTO add(Region reg, TokenDTO t) throws JsonProcessingException, IOException
   {
	   ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	
	   String request = "{\"regiondto\":" + new ObjectMapper().writer().writeValueAsString(reg)
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
		
		String request = "{\"regionId\":" + id + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
	   	if(r==null)
		   r = new ResponseDTO();
	   	r.setError(ErrorManager.getInstance().CheckResult(r));
	   	
	   	return r;
	}

	@Override
	public ResponseDTO edit(Region reg, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"regiondto\":" + new ObjectMapper().writer().writeValueAsString(reg) 
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
		
		String request = "{\"state\":" + state + ",\"regionId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO delete(int regionId, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"regionId\":" + regionId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   delete);
    	  
	   	if(r==null)
		   r = new ResponseDTO();
	   	r.setError(ErrorManager.getInstance().CheckResult(r));
	   	
	   	return r;
	}


}
