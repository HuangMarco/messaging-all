CREATE TABLE employee
(
  employeeName varchar(100) NOT NULL,
  employeeId varchar(11) NOT NULL ,
  employeeAddress varchar(100) DEFAULT NULL,
  employeeEmail varchar(100) DEFAULT NULL,
  firstName varchar(30) DEFAULT NULL,
  lastName varchar(20) DEFAULT NULL,
  PRIMARY KEY (employeeId)
);

CREATE TABLE BUSINESS_EMPLOYEE
(
  employee_name varchar(100) NOT NULL,
  employee_id bigint NOT NULL ,
  employee_address varchar(100) DEFAULT NULL,
  employee_email varchar(100) DEFAULT NULL,
  first_name varchar(30) DEFAULT NULL,
  last_name varchar(20) DEFAULT NULL,
  PRIMARY KEY (employee_id)
);

CREATE SEQUENCE hibernate_sequence;

CREATE SEQUENCE my_seq_gen;

-- drop sequence hibernate_sequence
-- drop sequence my_seq_gen
