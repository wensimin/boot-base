package com.sping.project.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.sping.project.app.entity.User;

@Mapper
public interface UserMapper {
	
	@SelectProvider(type = UserMapperProvider.class, method = "findByNameLike")
	public List<User> findByNameLike(String name);
}
