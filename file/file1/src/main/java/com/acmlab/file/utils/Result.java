package com.acmlab.file.utils;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: file
 * @description:
 * @author: Lixin
 * @create: 2020/12/28 15:07
 **/
@Data
public class Result<T> {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回描述
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(200, "操作成功", data);
    }

    public static <T> Result<T> ok() {
        return new Result<T>(200, "操作成功");
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(500, "操作失败", data);
    }

    public static <T> Result<T> error() {
        return new Result<T>(500, "操作失败");
    }


}
