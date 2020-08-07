package com.huangshi.wuji.messaging.app.biz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_business")
public class BizEntity {


    //这里如果设置了sequence，即便你页面上设置了employee_id，那么数据库依然按照自增策略创建主键
    @Id
    @Column(name = "business_id")
    @SequenceGenerator(name="biz-seq-gen",sequenceName="BIZ_SEQ_GEN",initialValue=205, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="biz-seq-gen")
    private Long businessId;
}
