package com.huangshi.wuji.messaging.app.jpa.service.impl;

import com.huangshi.wuji.messaging.app.jpa.entity.JPAEmployee;
import com.huangshi.wuji.messaging.app.jpa.repository.JPAEmployeeRepository;
import com.huangshi.wuji.messaging.app.jpa.repository.PagingAndSortingJPAEmployeeRepository;
import com.huangshi.wuji.messaging.app.jpa.service.JPAEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JPAEmployeeServiceImpl implements JPAEmployeeService {

    @Autowired
    PagingAndSortingJPAEmployeeRepository pagingJPAEmployeeRepository;

    @Autowired
    JPAEmployeeRepository employeeRepo;

    @Override
    public List<JPAEmployee> findAllByEmployeeName(String employeeName) {

        //创建分页对象，第一页2个元素数
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);

        //创建分页对象，第二页5个元素数来分页
        Pageable secondPageWithFiveElements = PageRequest.of(1, 5);

        //查找所有的员工，使用firstPageWithTwoElements来请求
        Page<JPAEmployee> allEmployees = pagingJPAEmployeeRepository.findAll(firstPageWithTwoElements);

       //查找所有名字相同的员工，以5个作为单个分页中的条数
        List<JPAEmployee> allEmployeesWithSameName = pagingJPAEmployeeRepository.findAllByEmployeeName(employeeName, secondPageWithFiveElements);

        return allEmployeesWithSameName;

    }

    @Override
    public JPAEmployee addNewEmployee(JPAEmployee emp) {
        return employeeRepo.save(emp);
    }

    /**
     * 返回所有的对象，由前端传入下面参数
     * @param pageNo：第X页
     * @param pageSize： 该X页中包含的对象个数
     * @param sortBy： 按照sortBy条件来排序
     * @return
     */
    @Override
    public List<JPAEmployee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<JPAEmployee> pagedResult = pagingJPAEmployeeRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JPAEmployee>();
        }
    }

    /**
     * 不排序直接返回对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<JPAEmployee> getAllEmployeesWithoutSorting(Integer pageNo, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<JPAEmployee> pagedResult = pagingJPAEmployeeRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JPAEmployee>();
        }
    }

    @Override
    public List<JPAEmployee> getAllEmployeesWithSortingOnly(String sortBy) {

        //这里传入的sortBy是JPAEmployee的成员变量名
        Sort sortOrder = Sort.by(sortBy);

        List<JPAEmployee> list = (List<JPAEmployee>)pagingJPAEmployeeRepository.findAll(sortOrder);

        return list;
    }

    /**
     * 包含多个排序条件
     * @param firstSort： 第一个排序
     * @param secondSort： 第二个排序
     * @return
     */
    @Override
    public List<JPAEmployee> getAllEmployeesWithMultipleSortingOnly(String firstSortString, String secondSortString) {

        Sort firstSort = Sort.by(firstSortString);

        Sort secondSort = Sort.by(secondSortString);

        Sort groupBySort = firstSort.and(secondSort);

        List<JPAEmployee> list = (List<JPAEmployee>)pagingJPAEmployeeRepository.findAll(groupBySort);

        return list;
    }
}
