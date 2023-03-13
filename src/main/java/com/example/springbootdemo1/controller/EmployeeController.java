package com.example.springbootdemo1.controller;

import com.example.springbootdemo1.dao.DepartmentDao;
import com.example.springbootdemo1.dao.EmployeeDao;
import com.example.springbootdemo1.mapper.DepartmentMapper;
import com.example.springbootdemo1.mapper.EmployeeMapper;
import com.example.springbootdemo1.mapper.UserMapper;
import com.example.springbootdemo1.pojo.Department;
import com.example.springbootdemo1.pojo.Employee;
import com.example.springbootdemo1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    /*
    * 查询所有员工信息
    * */
    @GetMapping("/emps")
    public String adminList(Model model){
        List<Employee> employees1 = employeeMapper.adminList();
        System.out.println("======"+employees1);
        model.addAttribute("emp",employees1);
        return "emp/list";
    }

    /*@RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emp",employees);
        return "emp/list";
    }*/

    /*
    * 根据ID查询员工
    * */
    /*@GetMapping("/emps/{id}")
    public String adminListById(@PathVariable("id")int id,Model model){
        List<Employee> list = employeeMapper.adminListById(id);
        model.addAttribute("emp",list);
        return "emp/list";
    }*/

    /*@GetMapping("emp")
    public String toAdd(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }*/
    /*
    * 添加一个员工
    * */
    @PostMapping("emp")
    public String toAdd2(Employee employee){
        int depaId = employee.getDepartment().getId();
        employee.setDepaId(depaId);
        System.out.println("save======="+employee);
        int i = employeeMapper.addEmployee(employee);
        return "redirect:emps";
    }

    /*@PostMapping("emp")
    public String addEmp(Employee employee){
        System.out.println("save======="+employee);
        employeeDao.save(employee);
        return "redirect:emps";
    }*/

    //根据ID查询员工信息
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        Employee employee1 = employeeMapper.adminById(id);//根据ID查询员工信息
        model.addAttribute("empData",employee1);
        List<Department> departments = departmentMapper.adminList();//查询部门所有数据
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    /*
    * 修改员工信息
    * */
    @PostMapping("/updateEmp")
    public String updateEMP(Employee employee){
        int depaId = employee.getDepartment().getId();
        employee.setDepaId(depaId);
        employeeMapper.updateEmployee(employee);
        return "redirect:emps";
    }

    //修改员工信息
    /*@PostMapping("/updateEmp")
    public String updateEMP(Employee employee){
        employeeDao.save(employee);
        return "redirect:emps";
    }*/

    //删除员工
    /*@GetMapping("/delemp/{id}")
    public String delectEmp(@PathVariable("id")int id){
        employeeDao.deleat(id);
        return "redirect:/emps";
    }*/
    @GetMapping("/delemp/{id}")
    public String delectEmp(@PathVariable("id")int id){
        employeeMapper.deleteEmployee(id);
        return "redirect:/emps";
    }
}