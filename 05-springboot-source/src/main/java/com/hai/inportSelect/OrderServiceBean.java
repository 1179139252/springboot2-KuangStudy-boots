package com.hai.inportSelect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class OrderServiceBean {
    @Autowired
    private OrderBean orderBean;


    public void show(){

        orderBean.setOrder("学相伴课程");
        orderBean.setOrderId("1");

        log.info("当前的课程是{}",orderBean);
    }

}
