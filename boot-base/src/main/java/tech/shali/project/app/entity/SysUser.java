package tech.shali.project.app.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tech.shali.project.app.entity.base.DataEntity;

/**
 * 用户对象
 *
 * @author wensimin
 */
@Entity
public class SysUser extends DataEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private boolean enabled;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn
	List<SysRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Optional.ofNullable(roles).map(roles -> roles.stream().map(role -> role.getName())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList())).orElse(new ArrayList<>());
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

}
