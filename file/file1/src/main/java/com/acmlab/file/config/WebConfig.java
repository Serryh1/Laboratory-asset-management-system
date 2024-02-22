package com.acmlab.file.config;

import com.acmlab.file.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 过滤器，拦截器
 *
 * @program: file
 * @description: 过滤器，拦截器
 * @author: shiquan
 * @create: 2021-09-23 12:33
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MyInterceptor());
    }
}