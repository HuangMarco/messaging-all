package com.huangshi.wuji.messaging.app.jpa.service;


import com.huangshi.wuji.messaging.app.jpa.entity.JPAEmployee;

import java.util.List;

public interface JPAEmployeeService {

    List<JPAEmployee> findAllByEmployeeName(String employeeName);

    JPAEmployee addNewEmployee(JPAEmployee emp);

    //按照指定排序条件sortBy排序
    List<JPAEmployee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);

    //不排序
    List<JPAEmployee> getAllEmployeesWithoutSorting(Integer pageNo, Integer pageSize);

    //不分页，只排序，同时注意这里传入的sortBy是JPAEmployee的成员变量名
    List<JPAEmployee> getAllEmployeesWithSortingOnly(String sortBy);

    //多个排序
    List<JPAEmployee> getAllEmployeesWithMultipleSortingOnly(String firstSort, String secondSort);

}
