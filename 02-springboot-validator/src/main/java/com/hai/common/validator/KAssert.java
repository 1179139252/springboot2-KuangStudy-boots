package com.hai.common.validator;

import com.hai.common.result.ResultCodeEnum;
import com.hai.exception.ValidatorException;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * 自定义断言判断 前端参数是否为空
 */

public class KAssert {

    public static void isEmpty(@Nullable Object object,ResultCodeEnum resultCodeEnum) {
        if (StringUtils.isEmpty(object)) {
            throw new ValidatorException(resultCodeEnum);
        }

    }
}