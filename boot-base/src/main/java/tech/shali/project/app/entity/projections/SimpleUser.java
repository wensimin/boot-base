package tech.shali.project.app.entity.projections;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * 简单用户投影
 * 
 * @author wensimin
 *
 */
@Entity
@Table(name = "test_user")
@Immutable
public class SimpleUser {
	@Id
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
