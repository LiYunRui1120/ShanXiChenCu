package com.example.springbootdemo1.mapper;

import com.example.springbootdemo1.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    List<Department> departmentList(int id);
    List<Department> adminList();
    Department departmentListData(int id);

}
