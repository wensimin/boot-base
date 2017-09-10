package tech.shali.project.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import tech.shali.project.app.entity.TestUser;

@Mapper
public interface UserMapper {
	
	@SelectProvider(type = UserMapperProvider.class, method = "findByNameLike")
	public List<TestUser> findByNameLike(String name);
}
