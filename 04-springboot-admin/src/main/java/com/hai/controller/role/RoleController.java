package com.hai.controller.role;

import com.hai.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description:
 * @author: xuke
 * @time: 2021/6/29 20:49
 */
@Controller
public class RoleController extends BaseController {


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
