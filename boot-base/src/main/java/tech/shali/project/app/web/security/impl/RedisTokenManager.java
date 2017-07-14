package tech.shali.project.app.web.security.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import tech.shali.project.app.web.security.TokenManager;

/**
 * 基于 Redis 的令牌管理器
 *
 * @author bbear
 * @since 1.0.0
 */
@Repository
public class RedisTokenManager implements TokenManager {

	@Autowired
	StringRedisTemplate stringRedis;

	@Override
	public String createToken(String username) {
		String oldToken = stringRedis.opsForValue().get(username);
		if (!StringUtils.isEmpty(oldToken)) {
			return oldToken;
		}
		String token = UUID.randomUUID().toString();
		stringRedis.delete(username);
		stringRedis.opsForValue().set(token, username);
		stringRedis.opsForValue().set(username, token);
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		String value = stringRedis.opsForValue().get(token);
		return !StringUtils.isEmpty(value);
	}

	@Override
	public String getToken(String token) {
		return stringRedis.opsForValue().get(token);
	}

	@Override
	public void delToken(String token) {
		stringRedis.delete(token);
	}
}
