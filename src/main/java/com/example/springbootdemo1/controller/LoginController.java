package com.example.springbootdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.function.RequestPredicate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session){
        if (!StringUtils.isEmpty(username) && "password".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/.html";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "signin.html";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/signin";
    }

}
