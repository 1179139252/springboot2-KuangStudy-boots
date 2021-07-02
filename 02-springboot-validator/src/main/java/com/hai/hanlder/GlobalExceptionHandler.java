package com.hai.hanlder;

import com.alibaba.fastjson.JSON;
import com.hai.exception.OrderException;
import com.hai.common.result.ResultCodeEnum;
import com.hai.exception.ValidatorException;
import com.hai.pojo.ErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * 对参数为空异常进行统一处理
     */

    @ExceptionHandler(ValidatorException.class)
    public ErrorHandler makeExcepton(ValidatorException validatorException, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.builder().message(validatorException.getMessage()).status(validatorException.getCode()).build();
        log.error("请求的地址是：{},出现的异常是：{}", request.getRequestURL(), validatorException);
        return errorHandler;
    }

    /**
     * 对验证的统一异常进行统一处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorHandler handlerValiator(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<Map<String, String>> mapList = toValidatorMsg(fieldErrors);
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.PARAM_ERROR, e, JSON.toJSONString(mapList));
        return errorHandler;
    }



//    ValidatorException

    /**
     * 对验证异常进行统一处理
     * @param fieldErrorList
     * @return
     */
    private List<Map<String, String>> toValidatorMsg(List<FieldError> fieldErrorList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (FieldError fieldError : fieldErrorList) {
            Map<String, String> map = new HashMap<>();
            map.put("field", fieldError.getField());
            map.put("msg", fieldError.getDefaultMessage());
            mapList.add(map);
        }
        return mapList;
    }



}