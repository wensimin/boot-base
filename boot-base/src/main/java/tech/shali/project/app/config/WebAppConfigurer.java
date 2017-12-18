package tech.shali.project.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tech.shali.project.app.web.security.xss.XssStringJsonSerializer;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

	/**
	 * 描述 : xssObjectMapper
	 *
	 * @param builder
	 *            builder
	 * @return xssObjectMapper
	 */
	@Bean
	@Primary
	public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
		// 解析器
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		// 注册xss解析器
		SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
		xssModule.addSerializer(new XssStringJsonSerializer());
		objectMapper.registerModule(xssModule);
		// 返回
		return objectMapper;
	}

	@Value("${pageable.prefix}")
	private String pagePrefix;

	/**
	 * 分页参数注入器重写，增加前缀
	 * 
	 * @return 分页参数注入器
	 */
	@Bean
	public PageableArgumentResolver pageableArgumentResolver() {
		// 使用重写后的sort注入器创建page注入器
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver(
				sortArgumentResolver());
		resolver.setPrefix(pagePrefix + ".");
		return resolver;
	}

	/**
	 * 排序参数注入器重写，增加前缀
	 * 
	 * @return
	 */
	@Bean
	public SortArgumentResolver sortArgumentResolver() {
		SortHandlerMethodArgumentResolver resolver = new SortHandlerMethodArgumentResolver();
		resolver.setSortParameter(pagePrefix + ".sort");
		return resolver;
	}

	/**
	 * 参数注入器
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(pageableArgumentResolver());
		argumentResolvers.add(sortArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}

	@Value("${cors.control.maxAge}")
	private int maxAge;

	//@formatter:off
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("*")
		.maxAge(maxAge);
	}
	//@formatter:on
}
