package com.example.springbootdemo1.controller;

import com.example.springbootdemo1.mapper.DepartmentMapper;
import com.example.springbootdemo1.mapper.EmployeeMapper;
import com.example.springbootdemo1.pojo.Department;
import com.example.springbootdemo1.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/emps")
    public String adminList(Model model){
        System.out.println("111111111111");
        int id = 102;
        List<Department> departments = departmentMapper.departmentList(id);
        System.out.println("======"+departments);
        model.addAttribute("emp",departments);
        return "emp/list";
    }

}
