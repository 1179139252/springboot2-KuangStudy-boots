package com.hai.limit.aop;

import com.hai.common.result.ResultCodeEnum;
import com.hai.exception.ValidatorException;
import com.hai.limit.annotion.AccessLimiter;
import io.netty.util.internal.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.reflect.MethodDelegate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 自定义切面
 * @param
 * @return
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/16 11:44
 */

@Component
@Aspect
public class AccessLimiterAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier(value = "iplimit")
    private DefaultRedisScript<Boolean> iplimit;

    private static final Logger log = LoggerFactory.getLogger(AccessLimiterAspect.class);

    //     定义切入点
    @Pointcut("@annotation(com.hai.limit.annotion.AccessLimiter)")
    public void cut() {
        log.info("cut---->>>>");
    }

    // 拦截属于放入执行之前的行为，所以定义@Before通知
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
//      获得方法签名作为key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//       拿到执行的方法
        Method method = methodSignature.getMethod();

//        拿到当前的注解
        AccessLimiter annotation = method.getAnnotation(AccessLimiter.class);
//      如果没有增加accessLimiter注解说明不需要限流，直接返回

        if (annotation == null) {
            return;
        }
//        拿到注解的 值
        String key = annotation.key();
        Integer limit = annotation.limit();
        Integer timeout = annotation.timeout();

//       判断是否设置了key
        if (StringUtil.isNullOrEmpty(key)){
//            获取所有参数的类型
            Class<?>[] parameterTypes = method.getParameterTypes();
//            设置方法名字为key
            key = method.getName();

            // 如果有参数的话，用参数和key来确定唯一
            if (parameterTypes!=null){
                String paramTypes = Arrays.stream(parameterTypes).map(Class::getName)
                        .collect(Collectors.joining(","));
                log.info("param types : {}", paramTypes);
                key = key + "#" + paramTypes;
            }
        }
//        请求lua脚本
        List<String> keyList = Arrays.asList(key);
        Boolean flag = stringRedisTemplate.execute(iplimit, keyList,limit.toString(),timeout.toString());
        if (!flag){
            throw new ValidatorException(ResultCodeEnum.IP_REFRSHRETOFAST);
        }

    }


}
