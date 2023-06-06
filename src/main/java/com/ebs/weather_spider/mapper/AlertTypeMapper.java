package com.ebs.weather_spider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebs.weather_spider.entity.AlertType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Alexander
 * @description 针对表【alert_type(预警类型表)】的数据库操作Mapper
 * @createDate 2023-06-05 18:15:40
 * @Entity generator.domain.AlertType
 */
@Mapper
public interface AlertTypeMapper extends BaseMapper<AlertType> {

}




