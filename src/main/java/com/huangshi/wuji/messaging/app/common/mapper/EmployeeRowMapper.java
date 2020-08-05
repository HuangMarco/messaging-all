package com.huangshi.wuji.messaging.app.common.mapper;

import com.huangshi.wuji.messaging.app.common.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getString("employeeId"));
        emp.setEmployeeName(rs.getString("employeeName"));
        emp.setEmployeeEmail(rs.getString("employeeEmail"));
        return emp;
    }

}
