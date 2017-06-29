package es.smt.appfrigo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import es.smt.appfrigo.bean.security.UserAuth;

//public class CustomFilter implements Filter{
public class CustomFilter extends AbstractAuthenticationProcessingFilter {

	  private static final String DEFAULT_FILTER_PROCESSES_URL = "/";
//	  private static final String POST = "POST";

	  public CustomFilter () {
	    super(DEFAULT_FILTER_PROCESSES_URL);
	  }
	
    @Override
    public void destroy() {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
    
    	
            HttpServletRequest request = (HttpServletRequest) req;
            String timezone = request.getParameter("timezone");
        
            
            String language = request.getParameter("language");
           
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication!=null)
            {
            	UserAuth user = (UserAuth)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            	if(user!=null)
            	{
            		if(user.getToken()!=null)
            		{
            			if(timezone!=null && !timezone.equals(""))
            				user.getToken().setTimezone(timezone);
            			else if(request.getSession().getAttribute("timezone")!=null)
            				user.getToken().setTimezone((String)request.getSession().getAttribute("timezone"));
                   
            			if(language!=null && !language.equals(""))
            				user.getToken().setIdioma(language);
            			else if(request.getSession().getAttribute("language")!=null)
            				user.getToken().setIdioma((String)request.getSession().getAttribute("language"));
            		}
            	}
            }
           /* else
            {
            	UserAuth a = new UserAuth();
            	a.setToken(new TokenDTO());

            	
            	
            	SecurityContextHolder.getContext().setAuthentication(auth);
            	
            /*    if(timezone!=null && !timezone.equals(""))
                	auth.setTimezone(timezone);
                
                if(language!=null && !language.equals(""))
                	auth.setLanguange(language);*/
                
      //          SecurityContextHolder.getContext().setAuthentication(auth);
        //    }
            
            //List<String> ss = (List<String>) request.getAttributeNames();
     //       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//


            chain.doFilter(req, res);

    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException,
      IOException, ServletException {
//    	   String aa = request.getParameter("timezone");
//    	   System.out.println("filte2r------------------------>");
      // You'll need to fill in the gaps here.  See the source of
      // UsernamePasswordAuthenticationFilter for a working implementation
      // you can leverage.
    	return null;
    }

 /*   @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Do nothing
    }
*/
}