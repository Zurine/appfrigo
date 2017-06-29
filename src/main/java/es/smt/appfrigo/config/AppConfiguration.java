package es.smt.appfrigo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "es.smt.appfrigo")
@Import({ SecurityConfig.class })

//@Import({ WebInitializer.class, DispatcherConfig.class})
public class AppConfiguration {
	

}
