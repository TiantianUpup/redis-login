package com.h2t.study.config;

import com.h2t.study.interceptor.RestAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/02/21 14:57
 */
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //除登出接口以外 其余接口都需要进行拦截
        registry.addInterceptor(restAuthInterceptor()).excludePathPatterns("/logout/**").addPathPatterns("/**");
    }

    @Bean
    public RestAuthInterceptor restAuthInterceptor() {
        return new RestAuthInterceptor();
    }
}