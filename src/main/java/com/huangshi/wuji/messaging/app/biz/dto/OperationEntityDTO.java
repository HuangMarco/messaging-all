package com.huangshi.wuji.messaging.app.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntityDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    //分布式系统下，ID从来不是自增类型的，而是生成的：com.huangshi.wuji.messaging.app.util.SequenceGenerator
    private String operationId;

    private String bizType;

    private String businessId;

    private String operationType;

    private String operationContent;

    private String createdDate;

    private String updatedDate;

    private String namespaceId;

    private String applicationId;

    public Date getCreatedDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.createdDate);
    }

    public void setCreatedDate(Date createdDate, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.createdDate = dateFormat.format(createdDate);
    }

    public Date getUpdatedDateConverted(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.createdDate);
    }

    public void setUpdatedDate(Date updatedDate, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.updatedDate = dateFormat.format(updatedDate);
    }

    public String toString(){
        return String.format("OperationMessage[operationId=%d, operationType=%t, businessId=%b, operationContent=%c, namespaceId=%n, applicationId=%a, createdDate=%c]", operationId, operationType, businessId, operationType, namespaceId, applicationId, createdDate);
    }

}
