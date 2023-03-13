package com.example.springbootdemo1.dao;

import com.example.springbootdemo1.pojo.Department;
import com.example.springbootdemo1.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //员工所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    static {
        employees = new HashMap<Integer,Employee>();

        /*employees.put(1001,new Employee(1001,"张三","1111@163.com",1,new Department(101,"教学部"),101));
        employees.put(1002,new Employee(1002,"李四","1111@163.com",0,new Department(102,"市场部"),102));
        employees.put(1003,new Employee(1003,"王五","1111@163.com",1,new Department(103,"教研部"),103));
        employees.put(1004,new Employee(1004,"赵六","1111@163.com",0,new Department(104,"运营部"),104));
        employees.put(1005,new Employee(1005,"杨七","1111@163.com",0,new Department(105,"后勤部"),105));*/


    }

    public static Integer initId = 1006;
    //增加一个员工
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
//        employee.setDepartment(departmentDao.getDeparementById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //获得全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过ID查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //通过ID删除员工
    public void deleat(Integer id){
        employees.remove(id);
    }


    public List<Map<String, Object>> userList(){
        String sql = "select e.id,e.lastName,e.url,e.email,e.gender,e.birth,d.departmentName from boot.employee e,boot.department d  where e.depaId = d.id";
        List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);
        return listMap;
    }


    public static String getMapToString(Map<String,Object> map){
        Set<String> keySet = map.keySet();
        //将set集合转换为数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        //给数组排序(升序)
        Arrays.sort(keyArray);
        //因为String拼接效率会很低的，所以转用StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if ((String.valueOf(map.get(keyArray[i]))).trim().length() > 0) {
                sb.append(keyArray[i]).append(":").append(String.valueOf(map.get(keyArray[i])).trim());
            }
            if(i != keyArray.length-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
