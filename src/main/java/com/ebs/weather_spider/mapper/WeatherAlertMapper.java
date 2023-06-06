package com.ebs.weather_spider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebs.weather_spider.entity.WeatherAlert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Alexander
 * @description 针对表【weather_alert(气象预警表)】的数据库操作Mapper
 * @createDate 2023-06-05 18:15:40
 * @Entity generator.domain.WeatherAlert
 */
@Mapper
public interface WeatherAlertMapper extends BaseMapper<WeatherAlert> {

}




