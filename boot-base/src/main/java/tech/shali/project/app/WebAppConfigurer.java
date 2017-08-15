package tech.shali.project.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import tech.shali.project.app.web.security.SecurityInterceptor;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
	@Value("${cors.control.maxAge}")
	private int maxAge;
	@Autowired
	private SecurityInterceptor securityInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor);
		super.addInterceptors(registry);
	}
	// @formatter:off
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("*")
		.maxAge(maxAge);
	}
	// @formatter:on
}
