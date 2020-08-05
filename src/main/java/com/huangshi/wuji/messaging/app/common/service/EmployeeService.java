package com.huangshi.wuji.messaging.app.common.service;

import com.huangshi.wuji.messaging.app.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    void insertEmployee(Employee emp);

    void updateEmployee(Employee emp);

    void executeUpdateEmployee(Employee emp);

    void deleteEmployee(Employee emp);
}
