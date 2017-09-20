package tech.shali.project.app.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.shali.project.app.dao.CacheDao;
import tech.shali.project.app.entity.CacheEntity;
import tech.shali.project.app.service.SysUserService;
import tech.shali.project.app.web.base.BaseController;

@RestController
public class TestController extends BaseController {
	@Autowired
	private SysUserService service;
	@Autowired
	private CacheDao cacheDao;

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public Principal get(Principal principal) {
		service.loadUserByUsername(principal.getName());
		return principal;
	}

	@RequestMapping(value = "public", method = RequestMethod.GET)
	public String getPublic() {
		return "PUBLIC";
	}

	@RequestMapping(value = "actuator-test", method = RequestMethod.GET)
	@PreAuthorize(value = "hasRole('ACTUATOR')")
	public boolean getActuator(HttpServletRequest request) {
		return request.isUserInRole("ACTUATOR");
	}
	
	@RequestMapping(value = "cache", method = RequestMethod.GET)
	public List<CacheEntity> getCache() {
		return cacheDao.findAll();
	}
}
