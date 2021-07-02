package com.hai.controller;

import com.hai.exception.OrderException;
import com.hai.common.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("订单中心")
public class OrderController {

    @GetMapping("/createOrder")
    public void createOrder(@RequestParam("id") Integer id){
        if (id.equals(1)){
            throw  new OrderException(ResultCodeEnum.ORDER_CREATE_FAIL);

        }
    }

}
