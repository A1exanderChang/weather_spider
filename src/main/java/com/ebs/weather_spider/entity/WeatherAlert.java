package com.ebs.weather_spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 气象预警表
 *
 * @TableName weather_alert
 */
@TableName(value = "weather_alert")
@Data
public class WeatherAlert implements Serializable {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 预警id
     */
    @TableField(value = "alertid")
    private String alertid;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 预警等级。关联alert_level表
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 预警类型。关联alert_type表
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 预警图标
     */
    @TableField(value = "pic")
    private String pic;

    /**
     * 详情页面链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 预警文本。需要进入详情页获取
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发布时间
     */
    @TableField(value = "issuetime")
    private Date issuetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}