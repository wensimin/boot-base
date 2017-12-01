package tech.shali.project.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tech.shali.project.app.entity.SysUser;

public interface UserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
	SysUser findByUserName(String username);
}
