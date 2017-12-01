package tech.shali.project.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import tech.shali.project.app.entity.base.DataEntity;

/**
 * 角色对象
 * 
 * @author wensimin
 *
 */
@Entity
public class SysRole extends DataEntity {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
