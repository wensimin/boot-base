package com.sping.project.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sping.project.app.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
	Page<User> findByName(String name, Pageable pageable);
}
