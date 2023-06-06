package com.ebs.weather_spider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ebs.weather_spider.entity.AlertType;

/**
 * @author Alexander
 * @description 针对表【alert_type(预警类型表)】的数据库操作Service
 * @createDate 2023-06-05 18:15:40
 */
public interface AlertTypeService extends IService<AlertType> {
    /**
     * 初始化预警类型列表
     */
    void alertTypeMapInit();
}
