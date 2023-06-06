package com.ebs.weather_spider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebs.weather_spider.entity.WeatherCity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Alexander
 * @description 针对表【weather_city(天气城市表)】的数据库操作Mapper
 * @createDate 2023-06-05 18:15:40
 * @Entity generator.domain.WeatherCity
 */
@Mapper
public interface WeatherCityMapper extends BaseMapper<WeatherCity> {

}




