package com.huangshi.wuji.messaging.app.messaging.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_messaging")
public class MessagingEntity {

    //这里如果设置了sequence，即便你页面上设置了employee_id，那么数据库依然按照自增策略创建主键
    @Id
    @Column(name = "message_id")
    @SequenceGenerator(name="msg-seq-gen",sequenceName="MSG_SEQ_GEN",initialValue=205, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="msg-seq-gen")
    private Long messageId;

}
