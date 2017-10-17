package tech.shali.project.app.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import tech.shali.project.app.entity.SysUser;
import tech.shali.project.app.service.SysUserService;
import tech.shali.project.app.web.base.BaseController;

@RestController
public class TestController extends BaseController {

	@GetMapping(value = "user")
	public Principal get(Principal principal) {
		return principal;
	}

	@GetMapping(value = "public")
	public String getPublic() {
		return "PUBLIC";
	}

	@GetMapping(value = "actuator-test")
	@PreAuthorize(value = "hasRole('ACTUATOR')")
	public boolean getActuator(HttpServletRequest request) {
		return request.isUserInRole("ACTUATOR");
	}
}
