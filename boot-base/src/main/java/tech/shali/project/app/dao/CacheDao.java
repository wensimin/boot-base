package tech.shali.project.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tech.shali.project.app.entity.CacheEntity;

public interface CacheDao extends JpaRepository<CacheEntity, Long>, JpaSpecificationExecutor<CacheEntity> {
}
