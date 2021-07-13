package com.hai.inportSelect;


import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * 
 * 自定义开关注解 
 * @return 
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/6 13:52
 */


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(OrderImportSelect.class)
public @interface  OrderEnbleConfiguration {

}
