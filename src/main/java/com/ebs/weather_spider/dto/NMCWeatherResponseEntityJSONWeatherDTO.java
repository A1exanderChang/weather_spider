package com.ebs.weather_spider.dto;

import lombok.Data;

@Data
public class NMCWeatherResponseEntityJSONWeatherDTO {
    /**
     * 当前温度
     */
    private String temperature;

    /**
     * 相对湿度
     */
    private String humidity;

    /**
     * 降水量
     */
    private String rain;

    /**
     * 天气
     */
    private String info;

    /**
     * 体感温度
     */
    private String feelst;
}
