package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class CategoryService implements ICategoryService{

	private String categories =  "CategoryManagement.svc/json/List";
	private String add = "CategoryManagement.svc/json/New";
	private String get = "CategoryManagement.svc/json/Get";
	private String edit = "CategoryManagement.svc/json/Edit";
	private String activate = "CategoryManagement.svc/json/Activate";
	private String delete = "CategoryManagement.svc/json/Delete";
	private String deleteCascade = "CategoryManagement.svc/json/DeleteCascade";

	@Override
    public ResponseDTO list(TokenDTO t) throws JsonProcessingException, IOException
    {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

   	 	String request = "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

   	 	r = RestManager.getInstance().exchangePostWithString(request,   categories);
   	 
   	 	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
        
   }
	
   @Override
   public ResponseDTO add(Category c, TokenDTO t) throws JsonProcessingException, IOException
   {
	   ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	
	   String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(c)
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
	public ResponseDTO edit(Category c, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"dto\":" + new ObjectMapper().writer().writeValueAsString(c)
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
	public ResponseDTO delete(int categoryId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"categoryId\":" + categoryId
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   delete);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO deleteCascade(int categoryId, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"categoryId\":" + categoryId
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   deleteCascade);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}
	
}
