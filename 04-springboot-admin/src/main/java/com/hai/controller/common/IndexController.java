package com.hai.controller.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController{


/**
 *
 * 跳转到首页
 * @return java.lang.String
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/1 11:00
 */

    @GetMapping("/index")
    public String index(){

        return "index";
    }

}
