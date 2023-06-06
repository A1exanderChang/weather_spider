package com.ebs.weather_spider.dto;

import lombok.Data;

import java.util.List;

@Data
public class NMCAlertResponseEntityJSONPageDTO {
    private List<NMCAlertResponseEntityJSONAlertDTO> list;
}
