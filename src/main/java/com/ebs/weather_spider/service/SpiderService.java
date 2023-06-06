package com.ebs.weather_spider.service;

import com.ebs.weather_spider.entity.WeatherCity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface SpiderService {
    /**
     * 城市天气爬虫
     *
     * @param weatherCity
     * @throws URISyntaxException
     */
    void weatherCitySpider(WeatherCity weatherCity) throws URISyntaxException, IOException;

    /**
     * 气象预警爬虫
     */
    void weatherAlertSpider() throws URISyntaxException, IOException;
}
