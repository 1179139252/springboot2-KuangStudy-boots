package com.hai.config;

import com.hai.hanlder.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    
/**
 * 
 * 登录拦截器注册 
 * @return com.hai.hanlder.LoginHandlerInterceptor
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/1 16:07
 */

    @Bean
    public LoginHandlerInterceptor UserLoginInterceptor() {

        return new LoginHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(UserLoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login", "/admin/logout","/admin/toLogin");
    }
}
