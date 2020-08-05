package com.huangshi.wuji.messaging.app.jpa.repository;

import com.huangshi.wuji.messaging.app.jpa.entity.JPAEmployee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagingAndSortingJPAEmployeeRepository extends PagingAndSortingRepository<JPAEmployee, Long> {

    //根据业务场景添加方法，其实一般情况下不添加任何方法都可以，因为原生的PagingAndSortingRepository已经满足需要了
    List<JPAEmployee> findAllByEmployeeName(String employeeName, Pageable pageable);

}
