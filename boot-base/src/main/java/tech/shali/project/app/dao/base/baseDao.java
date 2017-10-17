package tech.shali.project.app.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.shali.project.app.entity.base.DataEntity;

import java.io.Serializable;

/**
 * 公共basedao
 * @param <T>
 * @param <ID>
 */
public interface baseDao<T extends DataEntity,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {
    
}
