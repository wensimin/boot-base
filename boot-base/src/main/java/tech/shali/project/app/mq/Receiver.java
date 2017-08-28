package tech.shali.project.app.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import tech.shali.project.app.entity.User;

/**
 * mq消费者类
 * 
 * @author wensimin
 *
 */
@Component
@RabbitListener(queues = "${spring.rabbitmq.queue.name}")
public class Receiver {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RabbitHandler
	public void process(User user) {
		logger.debug("Receiver : " + user);
	}
}
