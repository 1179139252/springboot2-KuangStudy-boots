package com.hai.limit.annotion;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiter {

    String key() default "";

    int limit() default 1;

    int timeout() default 1;


}
