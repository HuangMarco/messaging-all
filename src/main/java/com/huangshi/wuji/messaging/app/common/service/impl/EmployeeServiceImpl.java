package com.huangshi.wuji.messaging.app.common.service.impl;

import com.huangshi.wuji.messaging.app.common.dao.EmployeeDAO;
import com.huangshi.wuji.messaging.app.common.service.EmployeeService;
import com.huangshi.wuji.messaging.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeDAO employeeDao;

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
    @Override
    public void insertEmployee(Employee emp) {
        employeeDao.insertEmployee(emp);

    }
    @Override
    public void updateEmployee(Employee emp) {
        employeeDao.updateEmployee(emp);

    }
    @Override
    public void executeUpdateEmployee(Employee emp) {
        employeeDao.executeUpdateEmployee(emp);

    }

    @Override
    public void deleteEmployee(Employee emp) {
        employeeDao.deleteEmployee(emp);

    }
}
