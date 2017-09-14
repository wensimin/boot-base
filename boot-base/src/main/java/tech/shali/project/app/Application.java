package tech.shali.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
/**
 *  启动类
 * @author wensimin
 *
 * @SpringBootApplication
 * 		基础配置
 * @EnableResourceServer
 * 		开启资源服务器，这样只有OAuth2通过的才能进入
 */
@SpringBootApplication
@EnableResourceServer
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
