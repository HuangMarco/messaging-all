package com.huangshi.wuji.messaging.app.messaging.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_operation")
public class OperationEntity {

    //这里如果设置了sequence，即便你页面上设置了employee_id，那么数据库依然按照自增策略创建主键
    @Id
    @Column(name = "operation_id")
    @SequenceGenerator(name="msg-seq-gen",sequenceName="MSG_SEQ_GEN",initialValue=205, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="msg-seq-gen")
    private Long messageId;

    @Column(name = "business_type")
    private String bizType;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "operation_content")
    private String operationContent;

    @Column(name = "operator_id")
    private Long operatorId;

    @Basic(optional = false)
    @Column(name = "created_date", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Basic(optional = false)
    @Column(name = "updated_date", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "namespace_id")
    private String namespaceId;

    @Column(name = "app_id")
    private String applicationId;





}
