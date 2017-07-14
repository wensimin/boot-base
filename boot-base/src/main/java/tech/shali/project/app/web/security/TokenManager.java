package tech.shali.project.app.web.security;

/**
 * 令牌管理器
 *
 * @author bbear
 * @since 1.0.0
 */
public interface TokenManager {

    String createToken(String username);

    boolean checkToken(String token);
    
    String getToken(String token);
    
    void delToken(String token);
}
