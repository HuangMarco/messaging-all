package com.huangshi.wuji.messaging.app.common.dao.impl;

import com.huangshi.wuji.messaging.app.common.dao.EmployeeDAO;
import com.huangshi.wuji.messaging.app.common.mapper.EmployeeRowMapper;
import com.huangshi.wuji.messaging.app.common.translator.CustomSQLErrorCodeTranslator;
import com.huangshi.wuji.messaging.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate template;

    private SimpleJdbcCall simpleJdbcCall;

    private SimpleJdbcInsert simpleJdbcInsert;

    //SB会自动找到名为dataSource的数据源并注入
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        final CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        template = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("EMPLOYEE");

        // Commented as the database is H2, change the database and create procedure READ_EMPLOYEE before calling getEmployeeUsingSimpleJdbcCall
        //simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("READ_EMPLOYEE");
    }


    @Override
    public int countEmployee() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM EMPLOYEE", Integer.class);
    }

    @Override
    public List<Employee> findAll() {
        return template.query("select * from employee", new EmployeeRowMapper());
    }

    @Override
    public void insertEmployee(Employee emp) {
        final String sql = "insert into employee(employeeId, employeeName , employeeAddress,employeeEmail) values(:employeeId,:employeeName,:employeeEmail,:employeeAddress)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("employeeId", emp.getEmployeeId())
                .addValue("employeeName", emp.getEmployeeName())
                .addValue("employeeEmail", emp.getEmployeeEmail())
                .addValue("employeeAddress", emp.getEmployeeAddress());
        template.update(sql,param, holder);



        //另外一种方式
        /**
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ID", emp.getId());
        parameters.put("FIRST_NAME", emp.getFirstName());
        parameters.put("LAST_NAME", emp.getLastName());
        parameters.put("ADDRESS", emp.getAddress());

        return simpleJdbcInsert.execute(parameters);

         **/
    }

    @Override
    public void updateEmployee(Employee emp) {
        final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("employeeId", emp.getEmployeeId())
                .addValue("employeeName", emp.getEmployeeName())
                .addValue("employeeEmail", emp.getEmployeeEmail())
                .addValue("employeeAddress", emp.getEmployeeAddress());
        template.update(sql,param, holder);

    }

    @Override
    public void executeUpdateEmployee(Employee emp) {
        final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";
        Map<String,Object> map=new HashMap<>();
        map.put("employeeId", emp.getEmployeeId());
        map.put("employeeName", emp.getEmployeeName());
        map.put("employeeEmail", emp.getEmployeeEmail());
        map.put("employeeAddress", emp.getEmployeeAddress());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteEmployee(Employee emp) {
        final String sql = "delete from employee where employeeId=:employeeId";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("employeeId", emp.getEmployeeId());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });

    }


    public String getEmployeeUsingMapSqlParameterSource() {
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", 1);

        return template.queryForObject("SELECT FIRST_NAME FROM EMPLOYEE WHERE ID = :id", namedParameters, String.class);
    }

    public int getEmployeeUsingBeanPropertySqlParameterSource() {
        final Employee employee = new Employee();
        employee.setFirstName("James");

        final String SELECT_BY_ID = "SELECT COUNT(*) FROM EMPLOYEE WHERE FIRST_NAME = :firstName";

        final SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(employee);

        return template.queryForObject(SELECT_BY_ID, namedParameters, Integer.class);
    }

    public int[] batchUpdateUsingJDBCTemplate(final List<Employee> employees) {
        return jdbcTemplate.batchUpdate("INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, employees.get(i).getEmployeeId());
                ps.setString(2, employees.get(i).getFirstName());
                ps.setString(3, employees.get(i).getLastName());
                ps.setString(4, employees.get(i).getEmployeeAddress());
            }

            @Override
            public int getBatchSize() {
                return 3;
            }
        });
    }

    public int[] batchUpdateUsingNamedParameterJDBCTemplate(final List<Employee> employees) {
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(employees.toArray());
        final int[] updateCounts = template.batchUpdate("INSERT INTO EMPLOYEE VALUES (:id, :firstName, :lastName, :address)", batch);
        return updateCounts;
    }

    public Employee getEmployeeUsingSimpleJdbcCall(int id) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
        Map<String, Object> out = simpleJdbcCall.execute(in);

        Employee emp = new Employee();
        emp.setFirstName((String) out.get("FIRST_NAME"));
        emp.setLastName((String) out.get("LAST_NAME"));

        return emp;
    }
}
