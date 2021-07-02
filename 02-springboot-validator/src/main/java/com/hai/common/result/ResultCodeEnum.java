package com.hai.common.result;

import lombok.Getter;

/**
 * @Author xuke
 * @Description 专门处理异常
 * @Date 21:14 2021/6/25
 * @Param
 * @return
 **/
@Getter
public enum ResultCodeEnum {
    UNKNOWN_REASON(false, 20001, "未知错误"),
    SERVER_ERROR(false, 500, "服务器忙，请稍后在试"),
    ORDER_CREATE_FAIL(false, 601, "订单下单失败"),
    NICK_NAME_CHECK(false, 602, "参数非法不能为空"),
    PARAM_ERROR(false,401,"参数校验不通过！");

    private Boolean success;
    private Integer code;
    private String message;

    private ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}