package tech.shali.project.app.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.shali.project.app.entity.User;
import tech.shali.project.app.entity.eunm.UserType;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		User user = new User();
		user.beforeInsert();
		user.setId(1L);
		user.setName("test");
		user.setType(UserType.a);
		System.out.println("Sender : " + user);
		this.rabbitTemplate.convertAndSend("hello", user);
	}
}