package tech.shali.project.app.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class UserMapperProvider {
	public String findByNameLike(String name) {
		return new SQL() {
			{
				SELECT("*");
				FROM("user");
				if (!StringUtils.isEmpty(name)) {
					WHERE("name like CONCAT(\"%\",#{name},\"%\")");
				}
			}
		}.toString();
	}
}
