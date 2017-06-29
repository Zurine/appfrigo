package es.smt.appfrigo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.SellerDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.model.UserAdminDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class UserService implements IUserService {

	private String list = "UserManagement.svc/json/listUsers";
	private String add = "UserManagement.svc/json/newUserAdmin"; 
	private String edit = "UserManagement.svc/json/EditUserAdmin"; 
	private String activate = "UserManagement.svc/json/ActivateUser";
	private String changePassword = "AutenticationManagement.svc/json/ChangePass";
	private String types = "UserManagement.svc/json/ListUserType";
	private String get = "UserManagement.svc/json/GetUser";  
	private String delete = "UserManagement.svc/json/DeleteAdmin";  
	private String editPassword = "SellerManagement.svc/json/EditSellerPassword";
	private String updateImage = "UserManagement.svc/json/UpdateImage";
	private String getBusiness = "UserManagement.svc/json/GetUserBusiness";
	private String updateSettings = "UserManagement.svc/json/UpdateUserSettings";
	
	@Override
	public ResponseDTO list(int state, TokenDTO t) throws JsonProcessingException, IOException
	{
		 ResponseDTO r = RestUtils.getInstance().InitializeResponse();

		 String request = "{\"state\":" + state + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)  + "}";

         r = RestManager.getInstance().exchangePostWithString(request,   list);
       

         return r;
	}
	
	@Override
    public ResponseDTO details(int id, TokenDTO t) throws JsonProcessingException, IOException
    {
        ResponseDTO r = RestUtils.getInstance().InitializeResponse();
        r = get(id, t);

        return r;
    }
    
	@Override
    public ResponseDTO get(int id, TokenDTO t) throws JsonProcessingException, IOException
    {
    	  ResponseDTO r = RestUtils.getInstance().InitializeResponse();

    	  String request= "{\"userId\":" + id + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
       
    	   r = RestManager.getInstance().exchangePostWithString(request,   get);
    	  
    	   if(r==null)
    		   r = new ResponseDTO();
    		r.setError(ErrorManager.getInstance().CheckResult(r));

        return r;
    }
	@Override
	   public ResponseDTO add(UserAdminDTO u,List<Integer>business, TokenDTO t) throws JsonProcessingException, IOException
	   {
		   ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		   //if(u.getBirthDate(). < new Date())
		   {
			   String request = "{\"userdto\":" + new ObjectMapper().writer().writeValueAsString(u) 
					   + ",\"businessId\":" +new ObjectMapper().writer().writeValueAsString(business)    + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

			   r = RestManager.getInstance().exchangePostWithString(request,   add);
		    	  
			   if(r==null)
				   r = new ResponseDTO();
				r.setError(ErrorManager.getInstance().CheckResult(r));
		   }
		   
		   return r;
	   }

	@Override
	public ResponseDTO edit(UserAdminDTO u, TokenDTO t) throws JsonProcessingException, IOException {

	   ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	   //if(u.getBirthDate(). < new Date())
	   {
		   String request = "{\"userdto\":" + new ObjectMapper().writer().writeValueAsString(u)
                    + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
		   r = RestManager.getInstance().exchangePostWithString(request,   edit);
	    	  
		   if(r==null)
			   r = new ResponseDTO();
			r.setError(ErrorManager.getInstance().CheckResult(r));
	   }
	   
	   return r;
	}
	

	@Override
	public ResponseDTO activate(boolean state, int id, TokenDTO t) throws JsonProcessingException, IOException {

		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"state\":" + state + ",\"userId\":" + id
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   activate);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO changePassword(String currentPass, String newPass, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"currentpass\":" + new ObjectMapper().writer().writeValueAsString(currentPass) + ",\"newpass\":"
		+new ObjectMapper().writer().writeValueAsString(newPass)
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   changePassword);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO listTypes() throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		r = RestManager.getInstance().exchangePostWithString("",   types);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO delete(int userId, TokenDTO t) throws JsonProcessingException, IOException {
  	  ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  String request= "{\"userId\":" + userId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
     
  	   r = RestManager.getInstance().exchangePostWithString(request,   delete);
  	  
  	   if(r==null)
  		   r = new ResponseDTO();
  		r.setError(ErrorManager.getInstance().CheckResult(r));

      return r;
	}
	
	@Override
	public ResponseDTO editPassword(SellerDTO d, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
			
		String request = "{\"sellerdto\":" + new ObjectMapper().writer().writeValueAsString(d)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   editPassword);
		  	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;

	}

	@Override
	public ResponseDTO updateImage(String image, TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"image\":" + new ObjectMapper().writer().writeValueAsString(image)
	             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";

		r = RestManager.getInstance().exchangePostWithString(request,   updateImage);
		  	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}

	@Override
	public ResponseDTO getUserBusiness(int userId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

	  	  String request= "{\"userId\":" + userId + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
	     
	  	   r = RestManager.getInstance().exchangePostWithString(request,   getBusiness);
	  	  
	  	   if(r==null)
	  		   r = new ResponseDTO();
	  		r.setError(ErrorManager.getInstance().CheckResult(r));

	      return r;
	}

	@Override
	public ResponseDTO updateSetttings(int action, boolean state, TokenDTO t)
			throws JsonProcessingException, IOException {
ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"action\":" + action + ",\"state\":"
		+state
             + ",\"token\":" + new ObjectMapper().writer().writeValueAsString(t)+ "}";
	
		r = RestManager.getInstance().exchangePostWithString(request,   updateSettings);
    	  
		if(r==null)
			r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));

	   return r;
	}
}
