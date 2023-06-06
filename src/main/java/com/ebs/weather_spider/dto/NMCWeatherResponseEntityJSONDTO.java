package com.ebs.weather_spider.dto;

import lombok.Data;

/**
 * 中国气象台响应体JSON
 */
@Data
public class NMCWeatherResponseEntityJSONDTO {
    private String msg;
    private Integer code;
    private NMCWeatherResponseEntityJSONDataDTO data;
}
