package com.hai.hanlder;

import com.hai.common.result.ResultCodeEnum;
import lombok.*;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:32
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ErrorHandler {
    // 异常的状态码，从枚举中获得
    private Integer status;
    // 异常的消息，写用户看得懂的异常，从枚举中得到
    private String message;
    // 异常的名字
    private String exception;

    /**
     * 对异常处理进行统一封装
     *
     * @param resultCodeEnum
     * @param throwable
     * @param message
     * @return
     */
    public static ErrorHandler fail(ResultCodeEnum resultCodeEnum, Throwable throwable, String message) {
        ErrorHandler errorHandler = ErrorHandler.fail(resultCodeEnum, throwable);
        errorHandler.setMessage(message);
        return errorHandler;
    }

    /**
     * 对异常枚举进行封装
     *
     * @param resultCodeEnum
     * @param throwable
     * @return
     */
    public static ErrorHandler fail(ResultCodeEnum resultCodeEnum, Throwable throwable) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(resultCodeEnum.getMessage());
        errorHandler.setStatus(resultCodeEnum.getCode());
        errorHandler.setException(throwable.getClass().getName());
        return errorHandler;
    }
}