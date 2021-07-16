package com.hai.exception;

import com.hai.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@Data
public class OrderException extends RuntimeException {
    private Integer code;
    private String message;

    public OrderException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public OrderException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}