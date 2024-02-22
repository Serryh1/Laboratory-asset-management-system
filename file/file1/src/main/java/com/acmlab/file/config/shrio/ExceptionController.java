package com.acmlab.file.config.shrio;

import com.acmlab.file.utils.Result;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionController 负责对 Controller中抛出的异常进行捕获处理
 *
 * @program: NovelClient
 * @author: lixin
 * @create: 2021-06-01 15:40
 **/
@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Object handleShiroException(ShiroException e) {
        return new Result<Object>(601, "shiro异常" + e.getMessage());
    }

//    // 捕捉其他所有异常
//    @ExceptionHandler(Exception.class)
//    public Object globalException(HttpServletRequest request, Throwable e) {
//        return new Result<Object>("501", e.getMessage());
//    }
}