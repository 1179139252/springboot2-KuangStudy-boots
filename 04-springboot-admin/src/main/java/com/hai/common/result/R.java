package com.hai.common.result;


import lombok.Data;

/**
 * 统一返回R类
 */

@Data
public class R {
    //    返回的编号
    private Integer code;
    //    返回的数据
    private Object data;
    //    返回的消息
    private String message;

//    私有化构造函数防止NEw
    private R(){}

    /**
     * 成功返回
     *
     * @param data
     * @param message
     * @return
     */
    public static R succsc(Object data,String message) {

        R r = new R();
        r.setCode(RResponseEnum.SUCCESS.getCode());
        r.setData(data);
        r.setMessage(message==null? RResponseEnum.SUCCESS.getMessage() : message);
        return r;

    }

    /**
     * 成功返回
     *
     * @param data
     * @param data
     * @return
     */
    public static R succsc(Object data) {
        return succsc(data, null);

    }

    /**
     * 失败返回
     *
     * @param code
     * @param message
     * @return
     */
    public static R fail(Integer code,String message) {

        R r = new R();
        r.setCode(code);
        r.setData(null);
        r.setMessage(message);
        return r;

    }



    /**
     * 失败返回
     *传入枚举类型
     * @param rResponseEnum
     * @param rResponseEnum
     * @return
     */
    public static R fail(RResponseEnum rResponseEnum) {

        R r = new R();
        r.setCode(rResponseEnum.getCode());
        r.setData(null);
        r.setMessage(rResponseEnum.getMessage());
        return r;

    }


    public static R fail(ResultCodeEnum resultCodeEnum) {

        R r = new R();
        r.setCode(resultCodeEnum.getCode());
        r.setData(null);
        r.setMessage(resultCodeEnum.getMessage());
        return r;

    }



}
