package com.ebs.weather_spider.controller;

import com.ebs.weather_spider.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class HelloController {
    @Autowired
    private SpiderService spiderService;

    @GetMapping("hello")
    public String hello() throws URISyntaxException, IOException {
        //spiderService.weatherAlertSpider();
        return "hello";
    }
}
