package tech.shali.project.app;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit mq config
 * 
 * @author wensimin
 *
 */
@Configuration
public class RabbitConfig {
	@Value("${spring.rabbitmq.queue.name}")
	private String queueName;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
}
