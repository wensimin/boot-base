package com.sping.project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sping.project.app.dao.UserDao;
import com.sping.project.app.entity.User;
import com.sping.project.app.entity.base.QueryPage;
import com.sping.project.app.mapper.UserMapper;

@Service
public class TestService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;

	public List<User> getAllUser(User user) {
		return userDao.findAll();
	}

	public Page<User> getPageUser(QueryPage<User> page) {
		if (page.getQuery() == null) {
			return userDao.findAll(page.getPageRequest());
		} else {
			//DEMO mapper test
			userMapper.findByNameLike(page.getQuery().getName());
			ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.contains());
			return userDao.findAll(Example.of(page.getQuery(), matcher), page.getPageRequest());
		}
	}

	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public User saveUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(Long id) {
		userDao.delete(id);
	}
}
