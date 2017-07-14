package com.sping.project.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sping.project.app.entity.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	Page<User> findByName(String name, Pageable pageable);
}
