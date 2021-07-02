package com.hai.controller;

import com.hai.common.validator.KAssert;
import com.hai.common.result.ResultCodeEnum;
import com.hai.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 14:46
 */
@RestController
@Api("用户校验")
@RequestMapping("/user")
public class UserValiatorController {
    @PostMapping("/valiator/reg")
    public UserVo createUser(@RequestBody @Validated UserVo userVo) {
        return userVo;
    }


    @PostMapping("/valiator/reg2")
    public void createUser2(String name,Integer age) {
//        Assert.isNull(name);
        KAssert.isEmpty(name, ResultCodeEnum.NICK_NAME_CHECK);
    }

}