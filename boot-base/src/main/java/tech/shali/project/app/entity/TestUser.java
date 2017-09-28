package tech.shali.project.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import tech.shali.project.app.entity.base.DataEntity;
import tech.shali.project.app.entity.eunm.UserType;

@Entity
@Table(name = "test_user")
public class TestUser extends DataEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column
	@Enumerated(EnumType.STRING)
	private UserType type;

	@ManyToMany
	@JoinColumn
	private List<UserAttr> attrs;

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

	public List<UserAttr> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<UserAttr> attrs) {
		this.attrs = attrs;
	}

}
