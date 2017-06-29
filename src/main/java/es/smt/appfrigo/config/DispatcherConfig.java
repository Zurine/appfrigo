package es.smt.appfrigo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.smt.appfrigo.interceptor.BreadcrumbInterceptor;


@Configuration
@Import({ThymeleafConfig.class})
public class DispatcherConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");  
        registry.addResourceHandler("/container/**").addResourceLocations("/resources/out/");  
        
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BreadcrumbInterceptor()).addPathPatterns("/**").excludePathPatterns("/setDefault");
    }

 
}