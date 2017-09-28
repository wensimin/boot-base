package tech.shali.project.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.shali.project.app.entity.TestUser;
import tech.shali.project.app.entity.UserAttr;
import tech.shali.project.app.entity.base.QueryPage;
import tech.shali.project.app.entity.projections.SimpleUser;
import tech.shali.project.app.service.TestService;
import tech.shali.project.app.web.base.BaseController;
import tech.shali.project.app.web.security.IgnoreSecurity;

@RestController
@RequestMapping("/user")
public class TestController extends BaseController {
	@Autowired
	private TestService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<TestUser> getPage(QueryPage<TestUser> page, TestUser query) {
		return service.getPageUser(page.setQuery(query));
	}
	@IgnoreSecurity
	@RequestMapping(value = "attr", method = RequestMethod.GET)
	public Page<TestUser> getPageByAttr(UserAttr attr) {
		return service.getPageByAttr(attr);
	}
	@IgnoreSecurity
	@RequestMapping(value = "sub", method = RequestMethod.GET)
	public Page<SimpleUser> getSub(QueryPage<TestUser> page, TestUser query) {
		return service.getSub(page.setQuery(query));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TestUser getById(@PathVariable("id") Long id) {
		return service.getUser(id);
	}

	@IgnoreSecurity
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@RequestBody TestUser user) {
		user.beforeInsert();
		user = service.saveUser(user);
		return tokenManager.createToken(user.getId().toString());
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public TestUser update(@RequestBody TestUser user) {
		user.beforeUpdate();
		return service.saveUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		service.deleteUser(id);
	}
}
