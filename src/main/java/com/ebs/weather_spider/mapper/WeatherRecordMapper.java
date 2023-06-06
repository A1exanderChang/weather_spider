package com.ebs.weather_spider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebs.weather_spider.entity.WeatherRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Alexander
 * @description 针对表【weather_record(天气记录表)】的数据库操作Mapper
 * @createDate 2023-06-05 18:15:40
 * @Entity generator.domain.WeatherRecord
 */
@Mapper
public interface WeatherRecordMapper extends BaseMapper<WeatherRecord> {

}




