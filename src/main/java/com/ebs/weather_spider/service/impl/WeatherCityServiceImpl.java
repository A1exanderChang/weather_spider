package com.ebs.weather_spider.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebs.weather_spider.config.GlobalVariables;
import com.ebs.weather_spider.entity.WeatherCity;
import com.ebs.weather_spider.mapper.WeatherCityMapper;
import com.ebs.weather_spider.service.WeatherCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alexander
 * @description 针对表【weather_city(天气城市表)】的数据库操作Service实现
 * @createDate 2023-06-05 18:15:40
 */
@Service
@Slf4j
public class WeatherCityServiceImpl extends ServiceImpl<WeatherCityMapper, WeatherCity>
        implements WeatherCityService {
    @Autowired
    private WeatherCityMapper weatherCityMapper;

    @Override
    public void initWeatherCityQueue() {
        List<WeatherCity> weatherCityList = new LambdaQueryChainWrapper<>(weatherCityMapper)
                .select(WeatherCity::getId, WeatherCity::getName, WeatherCity::getProvince, WeatherCity::getCode, WeatherCity::getUrl)
                .orderByAsc(WeatherCity::getId)
                .list();
        if (weatherCityList.size() < 1) {
            log.error("无可用的城市！");
            return;
        }
        weatherCityList.forEach(weatherCity -> {
            GlobalVariables.weatherCityQueue.offer(weatherCity);
        });
    }
}




