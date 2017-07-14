package tech.shali.project.app.web.base;

import org.springframework.beans.factory.annotation.Autowired;

import tech.shali.project.app.web.security.TokenManager;

public class BaseController {
	@Autowired
	protected TokenManager tokenManager;
}
