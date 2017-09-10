package tech.shali.project.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.shali.project.app.entity.SysUser;
import tech.shali.project.app.entity.base.QueryPage;
import tech.shali.project.app.web.base.BaseController;

@RestController
@RequestMapping("/user")
public class TestController extends BaseController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getPage(QueryPage<SysUser> page, SysUser query) {
		return "GET";
	}
}
