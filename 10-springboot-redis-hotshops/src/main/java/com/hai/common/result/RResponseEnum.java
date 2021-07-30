package com.hai.common.result;


/**
 * 统一返回枚举类
 *
 */


public enum RResponseEnum {


    USER_PASSWORD_CODE(402,"用户名密码错误"),
    USER_PASSWORD_CONFIRM(401,"2次密码不一致"),

    SUCCESS(200,"成功");


    private Integer code;
    private String message;

    RResponseEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
