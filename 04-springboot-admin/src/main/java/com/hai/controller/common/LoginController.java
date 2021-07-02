package com.hai.controller.common;


import com.hai.common.constant.RConstant;
import com.hai.common.result.ResultCodeEnum;
import com.hai.exception.ValidatorException;
import com.hai.pojo.user.User;
import com.hai.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    UserServiceImpl userService;

    /**
     *
     * 跳转到登录
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/1 10:55
     */


    /**
     * 跳转到登录界面 判断是否已经 登录
     *
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/2 10:29
     */

    @GetMapping("/login")
    public String login(HttpSession session) {

//        从session中拿到 已经登录的用户
        User user = (User) session.getAttribute(RConstant.USER_LOGIN_SESSION);

//      如果已经登录直接重定向到首页
        if (user != null) {
            log.info("当前已经登录{}", user.getNickname());
            return "redirect:/admin/index";
        } else {
            //      没登录跳转到登录界面
            log.info("当前没有登录跳转到登录界面...");
            return "login";
        }


    }

    /**
     * 业务真实登录
     *
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/1 16:39
     */


    @ResponseBody
    @PostMapping("/toLogin")
    public String toLogin(@RequestParam("nickname") String nickname, @RequestParam("password") String password, HttpSession session) {

        log.info("你当前的登录的用户名是{},密码是{}", nickname, password);
        User user = userService.getUserByName(nickname);
//        判断用户是否正确
        if (user == null) {
            log.info("用户名密码有误");
            throw new ValidatorException(ResultCodeEnum.NICK_NAME_ERROR);
        }
//        判断密码是否正确
        if (user != null && !user.getPassword().equalsIgnoreCase(password)) {
            log.info("密码有误");
            throw new ValidatorException(ResultCodeEnum.PASSWORD_ERROR);
        }

        session.setAttribute(RConstant.USER_LOGIN_SESSION, user);

        return "succ";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
//        销毁所有会话,跳转到登录界面
        session.invalidate();
        return "redirect:/admin/login";

    }


}
