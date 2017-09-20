package tech.shali.project.app;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HibernateCacheConfig {
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//
//	@Bean
//	public CacheManager cacheManager() {
//		return new RedisCacheManager(redisTemplate);
//	}
}