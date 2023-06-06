package com.ebs.weather_spider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebs.weather_spider.entity.WeatherAlert;
import com.ebs.weather_spider.mapper.WeatherAlertMapper;
import com.ebs.weather_spider.service.WeatherAlertService;
import org.springframework.stereotype.Service;

/**
 * @author Alexander
 * @description 针对表【weather_alert(气象预警表)】的数据库操作Service实现
 * @createDate 2023-06-05 18:15:40
 */
@Service
public class WeatherAlertServiceImpl extends ServiceImpl<WeatherAlertMapper, WeatherAlert>
        implements WeatherAlertService {

}




