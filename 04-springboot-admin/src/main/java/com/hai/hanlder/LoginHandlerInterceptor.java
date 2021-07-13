package com.hai.hanlder;

import com.alibaba.fastjson.JSON;
import com.hai.common.constant.RConstant;
import com.hai.common.result.R;
import com.hai.common.result.RResponseEnum;
import com.hai.common.result.ResultCodeEnum;
import com.hai.pojo.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截器
 *
 * @param
 * @author merry
 * @return
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/1 15:56
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//          response乱码处理
        response.setCharacterEncoding("utf-8");

        User user_login_session = (User) request.getSession().getAttribute(RConstant.USER_LOGIN_SESSION);
//        判断是否有session 存在 如果不存在 跳转到首页
        if (user_login_session == null) {

            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {

                String result = JSON.toJSONString(R.fail(ResultCodeEnum.SESSION_TIMEOUT));
                PrintWriter writer = response.getWriter();
                System.out.println(result);
                writer.println(result);
                writer.flush();
            } else {
                response.sendRedirect("/admin/login");
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
