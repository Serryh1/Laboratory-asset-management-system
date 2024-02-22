package com.acmlab.file.config.aop;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xingzi
 */
@Aspect
@Component
class RequestAspect {
    private final static Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    /** 以 controller 包下定义的所有请求为切入点 */
    @Pointcut("execution(*  *..*.*.controller..*.*(..))")
    public void webLog() {}

    /**
     * 在切点之前织入
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
        // 打印请求 url
        logger.info("请求URL         : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.info("请求方式         : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("方法路径         : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        logger.info("请求IP          : {}", request.getRemoteAddr());
        // 打印请求入参
//      try{ logger.info("请求参数         : {}", new Gson().toJson(joinPoint.getArgs()));
//    }catch ()
    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 每个请求之间空一行
    }

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("请求响应         : {}", new Gson().toJson(result));
        // 执行耗时
        logger.info("请求耗时         : {} ms", System.currentTimeMillis() - startTime);
        logger.info("=========================================== End ===========================================");
        logger.info("");
        return result;
    }
}
