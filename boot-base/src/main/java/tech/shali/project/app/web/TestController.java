package tech.shali.project.app.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.shali.project.app.web.base.BaseController;

@RestController
@RequestMapping("/user")
public class TestController extends BaseController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Principal getPage(Principal principal) {
		return principal;
	}
}
