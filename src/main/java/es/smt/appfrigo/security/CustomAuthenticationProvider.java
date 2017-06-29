package es.smt.appfrigo.security;


import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import es.smt.appfrigo.model.TokenDTO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String name = authentication.getName();
		
		// You can get the password here
	    String password = authentication.getCredentials().toString();
	     
	    TokenDTO t = new TokenDTO();
        t.setIdioma("en-US");
			
        CustomUserService  userService = new CustomUserService();
        
        UserDetails user =  userService.loadUser(name,password);
        
        if(user == null)
        	  throw new BadCredentialsException("Username not found.");
        
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        
        Authentication auth = new UsernamePasswordAuthenticationToken(user,user.getPassword(),authorities);
        
        
        return auth;
	}

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}