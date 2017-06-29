package es.smt.appfrigo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import es.smt.appfrigo.security.CustomAuthenticationProvider;
import es.smt.appfrigo.security.CustomUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
    	
    	.authorizeRequests() //"/home",
		.antMatchers("/", "/newPassword","/privacyPolicy","/setDefault", "/css/**","/out/**", "/js/**","/fonts/**").permitAll()  // Pantallas que no requieren autenticacin // ,"/auth/login"
		.anyRequest().authenticated()
		.and()
		.formLogin()
		 .loginPage("/login")
//		.loginPage("/")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		 .logout().permitAll()
		 .and()
//		.addFilterBefore(authenticationFilter(), AjaxTimeoutRedirectFilter.class)
		.csrf().disable();
    	
    	
//    	http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl() {
//            @Override
//            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                super.handle(request, response, accessDeniedException);
//
//                // 
//                // Your Code Here
//                //
//
//            }
//
//            @Override
//            public void setErrorPage(String errorPage) {
//                super.setErrorPage(errorPage);
//
//                // 
//                // Your Code Here
//                //
//
//            }
//        });
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter authenticationFilter() {
    	System.out.println("authenticationFilter********************************");
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(authenticator());
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
        authFilter.setAuthenticationManager(authenticationManager);
        authFilter.setRequiresAuthenticationRequestMatcher(    new AntPathRequestMatcher("/","POST"));
     
 
        authFilter.setUsernameParameter("username");
        authFilter.setPasswordParameter("password");
        return authFilter;
    }

		 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("*************************configureGlobal***********************************");
	
		 auth.authenticationProvider(new CustomAuthenticationProvider());
		
	}
	
	  @Bean
	  public AuthenticationProvider authenticator() {
	    return new CustomAuthenticationProvider();
	  }
}
