package com.hai.controller.user;

import com.hai.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController extends BaseController {

    @GetMapping("/user/list")
    public String role(){

        return "/user/template";
    }


}
