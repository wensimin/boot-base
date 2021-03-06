package tech.shali.project.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tech.shali.project.app.dao.UserDao;
import tech.shali.project.app.entity.SysUser;

@Service
public class SysUserService implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByUserName(username);
	}

    public void save(SysUser sysUser) {
	    userDao.saveAndFlush(sysUser);
    }
}
