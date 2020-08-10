package com.huangshi.wuji.messaging.app.biz.msgcontent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;

@Data
@NoArgsConstructor
public class BizRelatedMessage {

    private Long businessId;

    private String businessType;

    private String namespaceId;

    private String applicationId;

    private String createdDate;

    public BizRelatedMessage(@JsonProperty("businessId") Long businessId, @JsonProperty("businessType") String businessType, @JsonProperty("namespaceId") String namespaceId, @JsonProperty("applicationId") String applicationId, @JsonProperty("createdDate") String createdDate) {
        this.businessId = businessId;
        this.businessType = businessType;
        this.namespaceId = namespaceId;
        this.applicationId = applicationId;
        this.createdDate = createdDate;
    }

    public String toString(){
        return String.format("BizRelatedMessage[businessId=%d, businessType=%t, namespaceId=%n, applicationId=%a, createDate=%c]", businessId, businessType, namespaceId, applicationId, createdDate);
    }

}
