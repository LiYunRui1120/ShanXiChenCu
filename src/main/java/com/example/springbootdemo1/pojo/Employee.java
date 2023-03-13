package com.example.springbootdemo1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//员工表
@Data
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private String url;
    private String email;
    private Integer gender;
    private Department department;
    private Date birth;
    private Integer depaId;
    private Department departments;

    public Employee(Integer id, String lastName,String url, String email, Integer gender, Department department,Integer depaId) {
        this.id = id;
        this.lastName = lastName;
        this.url = url;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();
        this.depaId = depaId;
    }
}
