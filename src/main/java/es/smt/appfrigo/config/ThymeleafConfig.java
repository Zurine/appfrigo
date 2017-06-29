package es.smt.appfrigo.config;


import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan("es.smt.appfrigo")
@PropertySource("classpath:config.properties")
public class ThymeleafConfig {
 

	 @Bean
	 public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
	  return new PropertySourcesPlaceholderConfigurer();
	 }
	 
    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
       templateResolver.setCharacterEncoding("UTF-8");

        return templateResolver;
    }
    
    
    @Bean
    @Description("Thymeleaf view resolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
       viewResolver.setCharacterEncoding("UTF-8");
    //    viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.clearCache();
  
        return viewResolver;
    }

    
    
    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(securityDialect());
        templateEngine.setTemplateResolver(templateResolver());
        
        return templateEngine;
    }
    @Bean
    public LocaleResolver localeResolver(){
    	SessionLocaleResolver  resolver = new SessionLocaleResolver ();
    	resolver.setDefaultLocale(new Locale("en"));
    	return resolver;
    }
    
    
    @Bean(name ="messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
       // messageSource.
       
        return messageSource;
    }
    
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
      multipartResolver.setMaxUploadSize(4096 * 4096); //  * 4096 * 4096
      multipartResolver.setDefaultEncoding("ISO-8859-1");
      return multipartResolver;
    }
    

    @Bean
    public SpringSecurityDialect securityDialect() {
      return new SpringSecurityDialect();
    }
    
 
}
