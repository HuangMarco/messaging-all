package com.huangshi.wuji.messaging.app.dao.impl;

import com.huangshi.wuji.messaging.app.dao.EmployeeDAO;
import com.huangshi.wuji.messaging.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {


    JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void insertEmployee(Employee emp) {

    }

    @Override
    public void updateEmployee(Employee emp) {

    }

    @Override
    public void executeUpdateEmployee(Employee emp) {

    }

    @Override
    public void deleteEmployee(Employee emp) {

    }
}
