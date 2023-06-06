package com.ebs.weather_spider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class NMCWeatherResponseEntityJSONRealDTO {
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publish_time;

    /**
     * 天气信息
     */
    private NMCWeatherResponseEntityJSONWeatherDTO weather;

    /**
     * 风信息
     */
    private NMCWeatherResponseEntityJSONWindDTO wind;
}
