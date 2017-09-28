package tech.shali.project.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tech.shali.project.app.entity.projections.SimpleUser;

public interface SimpleUserDao  extends JpaRepository<SimpleUser, Long>, JpaSpecificationExecutor<SimpleUser>{

}
