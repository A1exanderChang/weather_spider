package com.ebs.weather_spider.dto;

import lombok.Data;

@Data
public class NMCAlertResponseEntityJSONDTO {
    private String msg;
    private Integer code;
    private NMCAlertResponseEntityJSONDataDTO data;
}
