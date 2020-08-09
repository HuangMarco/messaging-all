package com.huangshi.wuji.messaging.app.biz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizEntityDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Long businessId;

    private String businessType;

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


}