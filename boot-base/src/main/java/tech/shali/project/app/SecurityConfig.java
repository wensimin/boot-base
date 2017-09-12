package tech.shali.project.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security配置
 * 
 * @author wensimin
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 允许非验证路径
	 */
	@Override
	public void init(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/public/**");
	}
}
