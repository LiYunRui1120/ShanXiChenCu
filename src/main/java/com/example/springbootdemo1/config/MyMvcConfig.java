package com.example.springbootdemo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("signin");
        registry.addViewController("/.html").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/index1.html").setViewName("index1");
        registry.addViewController("/emp/list.html").setViewName("list");
        registry.addViewController("/widget.html").setViewName("widget");
        registry.addViewController("/emp/add.html").setViewName("add");
        registry.addViewController("/emp/update.html").setViewName("update");
        registry.addViewController("/form.html").setViewName("form");
        registry.addViewController("/table.html").setViewName("table");
        registry.addViewController("/chart.html").setViewName("chart");
        registry.addViewController("/signin.html").setViewName("signin");
        registry.addViewController("/signup.html").setViewName("signup");
        registry.addViewController("/404.html").setViewName("404");
        registry.addViewController("/blank.html").setViewName("blank");
        registry.addViewController("/button.html").setViewName("button");
        registry.addViewController("/typography.html").setViewName("typography");
        registry.addViewController("/element.html").setViewName("element");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerlnterceptor())
                .addPathPatterns("/**").
                excludePathPatterns("/","signin.html","/user/login","/css/**","/js/**","/font/**","/image/**");
    }
}
