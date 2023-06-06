package com.ebs.weather_spider.init;

import com.ebs.weather_spider.service.AlertLevelService;
import com.ebs.weather_spider.service.AlertTypeService;
import com.ebs.weather_spider.service.KeywordTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 全局变量初始化
 */
@Component
public class GlobalVariablesInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private KeywordTreeService keywordTreeService;
    @Autowired
    private AlertLevelService alertLevelService;
    @Autowired
    private AlertTypeService alertTypeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /**
         * 初始化关键字树
         */
        keywordTreeService.keywordTreeInit();
        /**
         * 初始化预警等级
         */
        alertLevelService.alertLevelMapInit();
        /**
         * 初始化预警类型
         */
        alertTypeService.alertTypeMapInit();
    }
}
