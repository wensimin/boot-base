package tech.shali.project.app.test;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import tech.shali.project.app.entity.User;
import tech.shali.project.app.entity.eunm.UserType;
import tech.shali.project.app.mq.Sender;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class HelloApplicationTests {	
	@Autowired
	private Sender sender;

	@Test
	public void hello() throws Exception {
		User user = new User();
		user.beforeInsert();
		user.setId(1L);
		user.setName("test");
		user.setType(UserType.a);
		sender.send(user);
	}
}