package com.example.springbootdemo1.controller;

import org.springframework.ui.Model;
import com.example.springbootdemo1.mapper.UserMapper;
import com.example.springbootdemo1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    //查询User表信息
    @GetMapping("/queryUserList")
    public String queryUserList(Model model){
        List<User> users = userMapper.queryUserList();
        model.addAttribute("departments",users);
        return "emp/add";
    }

    //根据ID查询信息
    @GetMapping("/queryUserById")
    public User queryUserById(){
        User user = userMapper.queryUserById(1);
        return user;
    }

    //添加一个用户
    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(5,"杨七","123465"));
        return "添加成功";
    }

    //修改用户信息
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(5,"杨七","一二三四五六"));
        return "修改成功";
    }

    //删除用户
    @GetMapping("/delectUser")
    public String deleteUser(){
        userMapper.deleteUser(5);
        return "删除成功";
    }
}
