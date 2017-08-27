package tech.shali.project.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import tech.shali.project.app.entity.base.DataEntity;
import tech.shali.project.app.entity.eunm.UserType;

@Entity
public class User extends DataEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column
	@Enumerated(EnumType.STRING)
	private UserType type;

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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "id:" + id + ",name:" + name + ",type:" + type;
	}
}
