package com.ebs.weather_spider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebs.weather_spider.entity.AlertLevel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Alexander
 * @description 针对表【alert_level(预警等级表)】的数据库操作Mapper
 * @createDate 2023-06-05 18:15:39
 * @Entity generator.domain.AlertLevel
 */
@Mapper
public interface AlertLevelMapper extends BaseMapper<AlertLevel> {

}




