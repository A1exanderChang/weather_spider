package com.ebs.weather_spider.dto;

import lombok.Data;

@Data
public class NMCWeatherResponseEntityJSONWindDTO {
    /**
     * 风向
     */
    private String direct;

    /**
     * 风力
     */
    private String power;

    /**
     * 风速
     */
    private String speed;
}
