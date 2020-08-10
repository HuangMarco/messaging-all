package com.huangshi.wuji.messaging.app.biz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizMessageEntityDTO {

    private String msgId;

    private Long bizId;

    private String businessType;

    private String status;

    private int sendTimes;

    private String namespaceId;

    private String applicationId;

    private String createdDate;

    private String updatedDate;

//    public BizRelatedMessage(@JsonProperty("businessId") Long businessId, @JsonProperty("businessType") String businessType, @JsonProperty("namespaceId") String namespaceId, @JsonProperty("applicationId") String applicationId, @JsonProperty("createdDate") String createdDate) {
//        this.businessId = businessId;
//        this.businessType = businessType;
//        this.namespaceId = namespaceId;
//        this.applicationId = applicationId;
//        this.createdDate = createdDate;
//    }


    public BizMessageEntityDTO(@JsonProperty("msgId") String msgId, @JsonProperty("bizId") Long bizId, @JsonProperty("status") String status, @JsonProperty("sendTimes") int sendTimes, @JsonProperty("namespaceId") String namespaceId, @JsonProperty("applicationId") String applicationId, @JsonProperty("createdDate") String createdDate, @JsonProperty("updatedDate") String updatedDate) {
        this.msgId = msgId;
        this.bizId = bizId;
        this.status = status;
        this.sendTimes = sendTimes;
        this.namespaceId = namespaceId;
        this.applicationId = applicationId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String toString(){
        return String.format("BizRelatedMessage[messageId=%m, businessId=%d, businessType=%t, namespaceId=%n, applicationId=%a, createDate=%c]", msgId, bizId, businessType, namespaceId, applicationId, createdDate);
    }

}
