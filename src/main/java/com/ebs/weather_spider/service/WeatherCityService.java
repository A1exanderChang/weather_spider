package com.ebs.weather_spider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ebs.weather_spider.entity.WeatherCity;

/**
 * @author Alexander
 * @description 针对表【weather_city(天气城市表)】的数据库操作Service
 * @createDate 2023-06-05 18:15:40
 */
public interface WeatherCityService extends IService<WeatherCity> {
    /**
     * 重新初始化天气城市队列
     */
    void initWeatherCityQueue();
}
