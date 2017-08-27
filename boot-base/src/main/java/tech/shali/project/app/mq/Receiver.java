package tech.shali.project.app.mq;

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
	@RabbitHandler
	public void process(User user) {
		System.out.println("Receiver : " + user);
	}
}
