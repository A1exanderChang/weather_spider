package com.ebs.weather_spider.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebs.weather_spider.config.GlobalVariables;
import com.ebs.weather_spider.entity.AlertType;
import com.ebs.weather_spider.mapper.AlertTypeMapper;
import com.ebs.weather_spider.service.AlertTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alexander
 * @description 针对表【alert_type(预警类型表)】的数据库操作Service实现
 * @createDate 2023-06-05 18:15:40
 */
@Service
public class AlertTypeServiceImpl extends ServiceImpl<AlertTypeMapper, AlertType>
        implements AlertTypeService {
    @Autowired
    private AlertTypeMapper alertTypeMapper;

    @Override
    public void alertTypeMapInit() {
        List<AlertType> alertTypeList = new LambdaQueryChainWrapper<>(alertTypeMapper)
                .orderByAsc(AlertType::getId)
                .list();
        Map<String, Integer> alertTypeMap = alertTypeList.stream()
                .collect(Collectors.toMap(AlertType::getName, AlertType::getId));
        GlobalVariables.alertTypeMap = alertTypeMap;
    }
}




