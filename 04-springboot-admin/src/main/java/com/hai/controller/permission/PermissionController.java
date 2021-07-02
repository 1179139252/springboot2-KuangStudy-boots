package com.hai.controller.permission;

import org.springframework.web.bind.annotation.GetMapping;

public class PermissionController {


    /**
     * @Author xuke
     * @Description 用户模板
     * @Date 20:50 2021/6/29
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/role/list")
    public String templatelist(){
        return "role/template";
    }
}
