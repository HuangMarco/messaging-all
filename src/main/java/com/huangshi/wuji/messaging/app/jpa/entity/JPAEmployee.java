package com.huangshi.wuji.messaging.app.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BUSINESS_EMPLOYEE")
public class JPAEmployee {

    //这里如果设置了sequence，即便你页面上设置了employee_id，那么数据库依然按照自增策略创建主键
    @Id
    @Column(name = "employee_id")
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN",initialValue=205, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private Long employeeId;

//    @Column(name = "employee_name", nullable=true, length=200)
    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Override
    public String toString(){
        return String.format(
                "Employee[id=%d, firstName='%s', lastName='%s']",
                employeeId, firstName, lastName);
    }
}
