package com.huangshi.wuji.messaging.app.common.dao;

import com.huangshi.wuji.messaging.app.common.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    void insertEmployee(Employee emp);
    void updateEmployee(Employee emp);
    void executeUpdateEmployee(Employee emp);
    public void deleteEmployee(Employee emp);
    int countEmployee();
}
