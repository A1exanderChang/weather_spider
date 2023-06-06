package com.ebs.weather_spider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NMCAlertResponseEntityJSONAlertDTO {
    /**
     * 预警id
     */
    private String alertid;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issuetime;

    /**
     * 标题
     */
    private String title;

    /**
     * 详情页面链接
     */
    private String url;

    /**
     * 预警图标
     */
    private String pic;
}
