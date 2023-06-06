package com.ebs.weather_spider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ebs.weather_spider.entity.AlertLevel;

/**
 * @author Alexander
 * @description 针对表【alert_level(预警等级表)】的数据库操作Service
 * @createDate 2023-06-05 18:15:39
 */
public interface AlertLevelService extends IService<AlertLevel> {
    /**
     * 初始化预警等级列表
     */
    void alertLevelMapInit();
}
