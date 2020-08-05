package com.huangshi.wuji.messaging.app.jpa.repository;

import com.huangshi.wuji.messaging.app.jpa.entity.JPAEmployee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAEmployeeRepository extends CrudRepository<JPAEmployee, Long> {

    List<JPAEmployee> findByLastName(String lastName);

    JPAEmployee findByEmployeeId(long id);

    @Query("SELECT JE FROM JPAEmployee JE WHERE LOWER(JE.firstName) = LOWER(:name)")
    JPAEmployee getByFirstName(String name);

}
