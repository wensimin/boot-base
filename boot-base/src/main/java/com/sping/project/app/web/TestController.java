package com.sping.project.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sping.project.app.entity.User;
import com.sping.project.app.entity.base.QueryPage;
import com.sping.project.app.service.TestService;
import com.sping.project.app.web.base.BaseController;
import com.sping.project.app.web.security.IgnoreSecurity;

@RestController
@RequestMapping("/user")
public class TestController extends BaseController {
	@Autowired
	private TestService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<User> getPage(QueryPage<User> page,User query) {
		return service.getPageUser(page.setQuery(query));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable("id") Long id) {
		return service.getUser(id);
	}

	@IgnoreSecurity
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@RequestBody User user) {
		user.beforeInsert();
		user = service.saveUser(user);
		return tokenManager.createToken(user.getId().toString());
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
		user.beforeUpdate();
		return service.saveUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		service.deleteUser(id);
	}
}
