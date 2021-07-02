package com.hai.config;

import com.hai.exception.OrderException;
import com.hai.exception.ResultCodeEnum;

import com.hai.pojo.ErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 对服务器端出现500异常进行统一处理
     */

    @ExceptionHandler(Throwable.class)
    public ErrorHandler makeExcepton(Throwable e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.SERVER_ERROR, e);
        log.error("请求的地址是：{},出现的异常是：{}", request.getRequestURL(), e);
        return errorHandler;
    }


    /**
     * 对订单异常进行统一处理
     */

    @ExceptionHandler(OrderException.class)
    public ErrorHandler makeExcepton(OrderException orderException, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.builder().message(orderException.getMessage()).status(orderException.getCode()).build();

        log.error("请求的地址是：{},出现的异常是：{}", request.getRequestURL(), orderException);
        return errorHandler;
    }



}