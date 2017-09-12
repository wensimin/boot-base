package tech.shali.project.app.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.shali.project.app.web.base.BaseController;

@RestController
public class TestController extends BaseController {

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public Principal get(Principal principal) {
		return principal;
	}

	@RequestMapping(value = "public", method = RequestMethod.GET)
	public String getPublic() {
		return "PUBLIC";
	}
}
