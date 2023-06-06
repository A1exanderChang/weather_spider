package com.ebs.weather_spider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebs.weather_spider.entity.WeatherRecord;
import com.ebs.weather_spider.mapper.WeatherRecordMapper;
import com.ebs.weather_spider.service.WeatherRecordService;
import org.springframework.stereotype.Service;

/**
 * @author Alexander
 * @description 针对表【weather_record(天气记录表)】的数据库操作Service实现
 * @createDate 2023-06-05 18:15:40
 */
@Service
public class WeatherRecordServiceImpl extends ServiceImpl<WeatherRecordMapper, WeatherRecord>
        implements WeatherRecordService {

}




