package com.hai.hanlder;

import com.alibaba.fastjson.JSON;
import com.hai.common.result.R;
import com.hai.pojo.ErrorHandler;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一拦截器 拦截所有的请求并且包装
 */
@ControllerAdvice("com.hai")
public class ResponseBodyHanlder implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

//       判断是否为异常返回，如果是使用r类进行包装
        if (body instanceof ErrorHandler) {
            ErrorHandler errorHandler = (ErrorHandler) body;
            return R.fail(errorHandler.getStatus(), errorHandler.getMessage());
//            需要对返回字符串的controller 进行单独处理.因为会进HttpMessageConverter
        } else if (body instanceof String){
//            使用fastjson进行转换 json
            return JSON.toJSONString(R.succsc(body));
        }

        return R.succsc(body);
    }
}
