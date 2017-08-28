package tech.shali.project.app.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.shali.project.app.entity.User;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void send(User user) {
		logger.debug("Sender : " + user);
		this.rabbitTemplate.convertAndSend("hello", user);
	}
}