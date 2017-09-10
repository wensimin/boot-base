package tech.shali.project.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tech.shali.project.app.entity.TestUser;

public interface UserDao extends JpaRepository<TestUser, Long>, JpaSpecificationExecutor<TestUser> {
	Page<TestUser> findByName(String name, Pageable pageable);
}
