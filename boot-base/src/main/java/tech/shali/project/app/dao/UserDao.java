package tech.shali.project.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tech.shali.project.app.entity.SysUser;

/**
 * 用户dao
 */
public interface UserDao extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {
	SysUser findByUserName(String username);
}
