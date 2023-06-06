package com.ebs.weather_spider.task;

import com.ebs.weather_spider.config.GlobalVariables;
import com.ebs.weather_spider.entity.WeatherCity;
import com.ebs.weather_spider.service.SpiderService;
import com.ebs.weather_spider.service.WeatherCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
@EnableScheduling
public class WeatherTask {
    @Autowired
    private WeatherCityService weatherCityService;
    @Autowired
    private SpiderService spiderService;

    /**
     * 城市天气爬虫定时任务
     */
    @Async
    @Scheduled(cron = "${cron.weather-city-cron}")
    public void weatherCitySpiderTask() throws URISyntaxException, IOException {
        // 如果队列中无元素，则重新初始化队列，并再次调用爬虫
        if (GlobalVariables.weatherCityQueue.size() < 1) {
            weatherCityService.initWeatherCityQueue();
            this.weatherCitySpiderTask();
        }
        WeatherCity weatherCity = GlobalVariables.weatherCityQueue.poll();
        spiderService.weatherCitySpider(weatherCity);
    }

    /**
     * 气象预警爬虫定时任务
     */
    @Async
    @Scheduled(cron = "${cron.weather-alert-cron}")
    public void weatherAlertSpiderTask() throws URISyntaxException, IOException {
        spiderService.weatherAlertSpider();
    }
}
