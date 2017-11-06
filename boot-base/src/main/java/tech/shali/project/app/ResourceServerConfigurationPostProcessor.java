package tech.shali.project.app;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.stereotype.Component;

/**
 * 资源服务器bean创建拦截，用于更改资源服务器的过滤排序
 */
@Component
public class ResourceServerConfigurationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ResourceServerConfiguration) {
            ResourceServerConfiguration config = (ResourceServerConfiguration) bean;
            config.setOrder(SecurityProperties.ACCESS_OVERRIDE_ORDER);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}