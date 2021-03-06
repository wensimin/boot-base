package tech.shali.project.app.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
