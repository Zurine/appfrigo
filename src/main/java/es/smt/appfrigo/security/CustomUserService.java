package es.smt.appfrigo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.smt.appfrigo.bean.security.Role;
import es.smt.appfrigo.bean.security.UserAuth;
import es.smt.appfrigo.constants.Constants;
import es.smt.appfrigo.constants.PropertiesManager;
import es.smt.appfrigo.model.LoginAdminDTO;
import es.smt.appfrigo.model.ResponseDTO;
import es.smt.appfrigo.model.TokenDTO;
import es.smt.appfrigo.service.AuthService;
import es.smt.appfrigo.utils.ListManager;

@Service
public class CustomUserService implements UserDetailsService{
	
	private Logger logger = Logger.getLogger(CustomUserService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		return null;
	}
	
	
	public UserDetails loadUser(String username, String password) throws UsernameNotFoundException {

		
		UserAuth user = null;
		
		TokenDTO t = new TokenDTO();
	    t.setIdioma("en-US");
	    
	    try {
	    	AuthService dd = new AuthService();
	    	long timeA = new Date().getTime();
			ResponseDTO response =  dd.login(t,username,password);
			long timeA1 = new Date().getTime();
			logger.info("Time call -> " + (timeA1 - timeA));
			if(response.getError().getCode() == Constants.codeOK){
				LoginAdminDTO loginDTO = (LoginAdminDTO)response.getResponse();
				if(loginDTO!=null)
				{
					Map<Integer,String> types = ListManager.getInstance().getUserType();
					
					user = new UserAuth();
					//1 : business
					//2 : enterprise
					//3 : superadmin
					List<Role> roles = new ArrayList<Role>();
					if(types!=null && loginDTO.getUsuario()!=null)
					{
						if(types.get(loginDTO.getUsuario().getType()).equals("SoyFrigo Admin"))
							roles.add(new Role("ROLE_SUPERADMIN"));
						else if(types.get(loginDTO.getUsuario().getType()).equals("Operator"))
							roles.add(new Role("ROLE_OPERATOR"));
						else if(types.get(loginDTO.getUsuario().getType()).equals("Supervisor"))
							roles.add(new Role("ROLE_SUPERVISOR"));
						else if(types.get(loginDTO.getUsuario().getType()).equals("Unilever Admin"))
							roles.add(new Role("ROLE_UNI"));
						else if(types.get(loginDTO.getUsuario().getType()).equals("MSM"))
							roles.add(new Role("ROLE_MSM"));
						else return null;
					}
					else return null;
					
					user.setEnterpriseId(loginDTO.getUsuario().getEnterpriseId());
					user.setPassword(password);
					user.setEmail(loginDTO.getUsuario().getEmail());
					user.setUsername(loginDTO.getUsuario().getName());
					user.setToken(loginDTO.getUsuario().getTokenDTO());
					user.setImage(loginDTO.getUsuario().getUrl());
					user.setSurname(loginDTO.getUsuario().getSurname());
					user.setAuthorities(roles);
					user.setCurrency(loginDTO.getUsuario().getCurrency());
					user.setUrl(PropertiesManager.getInstance().getProperty("web.url"));
					user.setItems(loginDTO.getUsuario().getItems());
					user.setEmailNot(loginDTO.getUsuario().isEmailNot());
				}
				long timeB = new Date().getTime();
				System.out.println("Time total -> " + (timeB - timeA));
				logger.info("Time total -> " + (timeB - timeA));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
		
	      
		return user;
	}


}
