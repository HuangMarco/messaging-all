package com.huangshi.wuji.messaging.app.biz.entity;

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
@Table(name="t_business_message")
public class BizMessageEntity {

    //这里不再设置自增策略，考虑分布式环境下不会设为自增，改为算法创建GUID
    @Id
    @Column(name = "msg_id")
    private String msgId;

    // TODO: 2020/8/10 使用JPA关联方式关联
    @Column(name = "biz_id")
    private Long bizId;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "message_status")
    private String status;

    @Column(name = "send_times")
    private int sendTimes;

    @Column(name = "namespace_id")
    private String namespaceId;

    @Column(name = "app_id")
    private String applicationId;

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


}
