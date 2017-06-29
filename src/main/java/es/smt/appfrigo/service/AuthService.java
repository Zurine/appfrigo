package es.smt.appfrigo.service;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.model.LoginAdminDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.rest.ErrorManager;
import es.smt.appfrigo.rest.ParseJSON;
import es.smt.appfrigo.rest.RestManager;
import es.smt.appfrigo.rest.RestUtils;

@Component
public class AuthService implements IAuthService, UserDetailsService{

	private static String login = "UserManagement.svc/json/loginAdmin";
	private static String newPassword = "AutenticationManagement.svc/json/RequestNewPass";
	private static String dashboard = "UserManagement.svc/json/GetDashboard";
	private static String dashboard2 = "UserManagement.svc/json/GetDashboard2";
	private static String rates = "UserManagement.svc/json/GetRates";
	private static String resetPassword = "AutenticationManagement.svc/json/ResetPasswordV2";
	
	@Override
	public ResponseDTO login(TokenDTO t, String username, String password) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"token\":" +  new ObjectMapper().writer().writeValueAsString(t) + ",\"email\":\"" + username + "\",\"password\":\"" + password + "\"}";

		
		r = RestManager.getInstance().exchangePostWithString(request,  login);
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		if(r.getResponse()!=null)
		{
			if (r.getError().getCode() == Constants.codeOK)
			{
				LoginAdminDTO loginDTO = ParseJSON.getInstance().getLoginAdminDTO(r.getResponse());
				if(loginDTO!=null)
				{
					t.setUserId(loginDTO.getUsuario().getId());
					t.setVersion("web");
					t.setEnterpriseId(loginDTO.getUsuario().getEnterpriseId());
					t.setToken(loginDTO.getUsuario().getToken());
					loginDTO.getUsuario().setTokenDTO(t);
					r.setResponse(loginDTO);
				}
			}
		}
		
		return r;
	}


	@Override
	public ResponseDTO requestPassword(String email) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
	
		String request = "{\"email\":\"" +  email + "\",\"enterpriseId\":3}";
		
		r = RestManager.getInstance().exchangePostWithString(request,   newPassword);
	    	  
		return r;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}


	@Override
	public ResponseDTO getDashboard(TokenDTO t) throws JsonProcessingException, IOException {
		
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   dashboard);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
        
	}


	@Override
	public ResponseDTO getDashboard2(TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();

  	  	String request= "{\"token\":" + new ObjectMapper().writer().writeValueAsString(t) + "}";
    	
  	  	r = RestManager.getInstance().exchangePostWithString(request,   dashboard2);
  	  
  	  	if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
	   
        return r;
	}


	@Override
	public ResponseDTO getRates(int equipmentId, TokenDTO t) throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
		String request = "{\"token\":" +  new ObjectMapper().writer().writeValueAsString(t) + ",\"equipmentId\":\"" + equipmentId + "\"}";
		
		r = RestManager.getInstance().exchangePostWithString(request,   rates);
	    	  
		return r;
	}



	@Override
	public ResponseDTO resetPassword(int userId, String resetPassCode, String newPass)
			throws JsonProcessingException, IOException {
		ResponseDTO r = RestUtils.getInstance().InitializeResponse();
		
	String request = "{\"userId\":" +  userId+ ",\"resetPassCode\":\"" + resetPassCode + "\",\"newPass\":\"" + newPass + "\"}";

		
		r = RestManager.getInstance().exchangePostWithString(request,  resetPassword);
		if(r==null)
		   r = new ResponseDTO();
		r.setError(ErrorManager.getInstance().CheckResult(r));
		
		
		return r;
	}
}
