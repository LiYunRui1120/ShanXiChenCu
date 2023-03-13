package com.example.springbootdemo1.mapper;

import com.example.springbootdemo1.pojo.Employee;
import com.example.springbootdemo1.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface EmployeeMapper {
    List<Employee> adminList();

    int addEmployee(Employee employee);
    List<Employee> adminListById(int id);
    Employee adminById(int id);
    int updateEmployee(Employee employee);
    int deleteEmployee(int id);
}
