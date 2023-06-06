package com.ebs.weather_spider.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
@ConfigurationProperties(prefix = "http-header")
public class HttpHeader {
    private HashMap<String, String> header;
}
