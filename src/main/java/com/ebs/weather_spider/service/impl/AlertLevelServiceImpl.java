package com.ebs.weather_spider.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebs.weather_spider.config.GlobalVariables;
import com.ebs.weather_spider.entity.AlertLevel;
import com.ebs.weather_spider.mapper.AlertLevelMapper;
import com.ebs.weather_spider.service.AlertLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alexander
 * @description 针对表【alert_level(预警等级表)】的数据库操作Service实现
 * @createDate 2023-06-05 18:15:39
 */
@Service
public class AlertLevelServiceImpl extends ServiceImpl<AlertLevelMapper, AlertLevel>
        implements AlertLevelService {
    @Autowired
    private AlertLevelMapper alertLevelMapper;

    @Override
    public void alertLevelMapInit() {
        List<AlertLevel> alertLevelList = new LambdaQueryChainWrapper<>(alertLevelMapper)
                .orderByAsc(AlertLevel::getId)
                .list();
        Map<String, Integer> alertLevelMap = alertLevelList.stream()
                .collect(Collectors.toMap(AlertLevel::getName, AlertLevel::getId));
        GlobalVariables.alertLevelMap = alertLevelMap;
    }
}




