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

    @Id
//    @Column(name = "employee_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
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
