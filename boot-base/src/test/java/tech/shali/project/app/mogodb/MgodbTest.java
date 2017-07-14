package tech.shali.project.app.mogodb;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import tech.shali.project.app.dao.UserMongoDao;
import tech.shali.project.app.entity.MongoUser;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class MgodbTest {	
	@Autowired
	private UserMongoDao userMongoDao;
	private Long count = 0L;

	@Test
	public void saveTest() {
		count = userMongoDao.count();
		MongoUser user = new MongoUser();
		user.setName("xiaohong");
		userMongoDao.save(user);
		System.out.println(count);
		Assert.assertEquals(count + 1L, userMongoDao.count());
	}

	@Test
	public void getTest() {
		userMongoDao.count();
		userMongoDao.findAll();
	}
}
