package com.sping.project.app.web.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.sping.project.app.web.security.TokenManager;

public class BaseController {
	@Autowired
	protected TokenManager tokenManager;
}
