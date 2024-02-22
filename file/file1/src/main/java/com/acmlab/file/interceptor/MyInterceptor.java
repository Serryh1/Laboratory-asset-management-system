package com.acmlab.file.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @program: file
 * @description: 拦截器
 * @author: shiquan
 * @create: 2021-09-23 09:29
 **/
public class MyInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(MyInterceptor.class);
    @Override
    /** 拦截方法访问之前调用 */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**return HandlerInterceptor.super.preHandle(request, response, handler)*/
        log.info("【MyInterceptor】调用了:{}", request.getRequestURI());
        request.setAttribute("requestTime", System.currentTimeMillis());
        return true;
    }

    @Override
    /** 拦截方法访问之后调用 */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    /** 整个流程结束调用 */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}