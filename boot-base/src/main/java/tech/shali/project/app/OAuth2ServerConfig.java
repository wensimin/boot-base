/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.shali.project.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import tech.shali.project.app.service.SysUserService;

/**
 * OAuth2相关配置
 * 
 * @author wensimin
 *
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory redisConnection;
	@Autowired
	private TokenStore tokenStore;
	@Value("${auth.token.expiration}")
	private int expiration;
	@Value("${auth.client}")
	private String client;
	@Value("${auth.secret}")
	private String secret;
	@Value("${auth.token.name}")
	private String name;

	/**
	 * 配置tokenStore为redis
	 * 
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnection);
	}

	/**
	 * 密码加密方式
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/**
	 * 过滤器，设置cors filter ，并且设置高排序
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.authenticationManager(authenticationManager);
		configurer.tokenStore(tokenStore);
		configurer.userDetailsService(sysUserService);
	}

	//@formatter:off
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(client)
		.secret(secret)
		.accessTokenValiditySeconds(expiration)
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.resourceIds("resource");
	}
	//@formatter:off
}
