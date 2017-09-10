package tech.shali.project.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tech.shali.project.app.dao.UserDao;
import tech.shali.project.app.entity.TestUser;
import tech.shali.project.app.entity.base.QueryPage;
import tech.shali.project.app.mapper.UserMapper;

@Service
public class TestService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;

	public List<TestUser> getAllUser(TestUser user) {
		return userDao.findAll();
	}

	public Page<TestUser> getPageUser(QueryPage<TestUser> page) {
		if (page.getQuery() == null) {
			return userDao.findAll(page.getPageRequest());
		} else {
			// mybatis query
			userMapper.findByNameLike(page.getQuery().getName());
			// predicate query
			userDao.findAll((root, query, cb) -> {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(page.getQuery().getName())) {
					list.add(cb.like(root.get("name").as(String.class), "%" + page.getQuery().getName() + "%"));
				}
				return query.where(cb.and(list.toArray(new Predicate[list.size()]))).getRestriction();
			});
			// QBE query
			ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.contains());
			return userDao.findAll(Example.of(page.getQuery(), matcher), page.getPageRequest());
		}
	}

	public TestUser getUser(Long id) {
		return userDao.findOne(id);
	}

	public TestUser saveUser(TestUser user) {
		return userDao.save(user);
	}

	public void deleteUser(Long id) {
		userDao.delete(id);
	}
}
