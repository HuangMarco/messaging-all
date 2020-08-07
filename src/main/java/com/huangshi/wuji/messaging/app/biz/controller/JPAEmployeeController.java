package com.huangshi.wuji.messaging.app.biz.controller;

import com.huangshi.wuji.messaging.app.jpa.entity.JPAEmployee;
import com.huangshi.wuji.messaging.app.jpa.service.JPAEmployeeService;
import com.huangshi.wuji.messaging.swagger.config.SwaggerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SwaggerConstants.API_JPA)
public class JPAEmployeeController {

    @Autowired
    JPAEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<JPAEmployee>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        //这里传入的sortBy是JPAEmployee的成员变量名
        List<JPAEmployee> list = employeeService.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<JPAEmployee>>(list, new HttpHeaders(), HttpStatus.OK);
    }


    //为JPAEmployee设置sequence，即便页面上设置了employee_id，那么数据库依然按照自增策略创建主键
    @PostMapping(value = "/add/new/employee")
    public void createJPAEmployee(@RequestBody JPAEmployee emp) {
        employeeService.addNewEmployee(emp);
    }
}
