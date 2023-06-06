package com.ebs.weather_spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 天气记录表
 *
 * @TableName weather_record
 */
@TableName(value = "weather_record")
@Data
public class WeatherRecord implements Serializable {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 城市名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 省份
     */
    @TableField(value = "province")
    private String province;

    /**
     * 中国气象台，城市编号
     */
    @TableField(value = "code")
    private String code;

    /**
     * 中国气象台，城市天气链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 当前气温
     */
    @TableField(value = "temperature")
    private String temperature;

    /**
     * 相对湿度
     */
    @TableField(value = "humidity")
    private String humidity;

    /**
     * 降水量
     */
    @TableField(value = "rain")
    private String rain;

    /**
     * 体感温度
     */
    @TableField(value = "feelst")
    private String feelst;

    /**
     * 天气
     */
    @TableField(value = "info")
    private String info;

    /**
     * 风向
     */
    @TableField(value = "wind_direct")
    private String wind_direct;

    /**
     * 风力
     */
    @TableField(value = "wind_power")
    private String wind_power;

    /**
     * 风速
     */
    @TableField(value = "wind_speed")
    private String wind_speed;

    /**
     * 更新时间
     */
    @TableField(value = "publish_time")
    private Date publish_time;

    /**
     * 创建时间，必备字段。默认为当前时间，不允许更改。
     */
    @TableField(value = "gmt_create")
    private Date gmt_create;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}