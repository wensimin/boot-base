package tech.shali.project.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tech.shali.project.app.dao.SimpleUserDao;
import tech.shali.project.app.dao.UserDao;
import tech.shali.project.app.entity.TestUser;
import tech.shali.project.app.entity.UserAttr;
import tech.shali.project.app.entity.base.QueryPage;
import tech.shali.project.app.entity.projections.SimpleUser;
import tech.shali.project.app.mapper.UserMapper;

@Service
public class TestService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SimpleUserDao simUserDao;
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

	/**
	 * 根据manyTomany 关系进行查询
	 * 
	 * @demo
	 * @param attr
	 * @return
	 */
	public Page<TestUser> getPageByAttr(UserAttr attr) {
		return userDao.findAll((root, query, cb) -> {
			Subquery<UserAttr> subquery = query.subquery(UserAttr.class);
			Root<TestUser> subqueryRoot = subquery.correlate(root);
			Join<TestUser, UserAttr> joins = subqueryRoot.join("attrs");
			subquery.select(joins).where(cb.equal(joins.get("id"), attr.getId()));
			return query.where(cb.exists(subquery)).getRestriction();
		}, new QueryPage<>().getPageRequest());
	}

	public Page<SimpleUser> getSub(QueryPage<TestUser> page) {
		return simUserDao.findAll((root, query, cb) -> {
			return query.where().getRestriction();
		}, page.getPageRequest());
	}
}
