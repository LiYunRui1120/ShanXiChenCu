package com.example.springbootdemo1.config;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerlnterceptor implements HandlerInterceptor {


    //登陆拦截器，如果没有登陆直接访问页面 是访问不了的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");//获取登陆信息
        if (loginUser == null){
            request.setAttribute("msg","请先登陆");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

}
