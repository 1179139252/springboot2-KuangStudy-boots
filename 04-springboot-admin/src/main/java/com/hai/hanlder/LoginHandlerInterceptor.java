package com.hai.hanlder;

import com.hai.common.constant.RConstant;
import com.hai.pojo.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 登录拦截器
 * @param
 * @return
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/1 15:56
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        User user_login_session = (User) request.getSession().getAttribute(RConstant.USER_LOGIN_SESSION);
//        判断是否有session 存在 如果不存在 跳转到首页
        if (user_login_session == null){

            response.sendRedirect("/admin/login");

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
