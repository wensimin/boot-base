package tech.shali.project.app.web.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tech.shali.project.app.web.exception.TokenException;

/**
 * 用于检查 token 的拦截器
 *
 * @author bbear
 * @since 1.0.0
 */
@Repository
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	@Value(value = "${auth.token.name}")
	private String tokenName = null;
	@Autowired
	private TokenManager tokenManager;

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从切点上获取目标方法
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			// 若目标方法忽略了安全性检查，则直接调用目标方法
			if (method.isAnnotationPresent(IgnoreSecurity.class)) {
				return true;
			}
			// 从 request header 中获取当前 token
			String token = request.getHeader(tokenName);
			// 检查 token 有效性
			if (StringUtils.isEmpty(token)) {
				throw new TokenException("需要token");
			}
			if (!tokenManager.checkToken(token)) {
				String message = String.format("token [%s] 是无效的", token);
				throw new TokenException(message);
			}
			// 调用目标方法
		}
		return true;
	}

}
