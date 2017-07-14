package com.sping.project.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sping.project.app.dao.UserDao;
import com.sping.project.app.dao.UserMongoDao;
import com.sping.project.app.entity.User;
import com.sping.project.app.entity.base.QueryPage;
import com.sping.project.app.mapper.UserMapper;

@Service
public class TestService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserMongoDao userMongoDao;

	public List<User> getAllUser(User user) {
		return userDao.findAll();
	}

	public Page<User> getPageUser(QueryPage<User> page) {
		if (page.getQuery() == null) {
			return userDao.findAll(page.getPageRequest());
		} else {
			// mongo find
			userMongoDao.findAll();
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

	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public User saveUser(User user) {
		// error! mongodb 不能使用Long id 
		// userMongoDao.save(user);
		return userDao.save(user);
	}

	public void deleteUser(Long id) {
		userDao.delete(id);
	}
}
