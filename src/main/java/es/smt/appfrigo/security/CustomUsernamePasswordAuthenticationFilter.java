package es.smt.appfrigo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String languageParam = request.getParameter("language");
        request.getSession().setAttribute("language", languageParam);
        System.out.println("***Filter**************" + languageParam);
        // You can do your stuff here
        return super.attemptAuthentication(request, response); 
    } 
    

}
