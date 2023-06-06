package com.ebs.weather_spider.config;

import cn.hutool.dfa.WordTree;
import com.ebs.weather_spider.entity.WeatherCity;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 全局变量
 *
 * @author Alexander Chang
 * @date 2023-6-5 18:39:30
 */
public class GlobalVariables {
    /**
     * 城市天气任务队列
     */
    public static Queue<WeatherCity> weatherCityQueue = new LinkedList<>();

    /**
     * 关键词树
     */
    public static WordTree keywordTree;

    /**
     * 预警等级列表Map
     * key为表中name，value为表中id
     */
    public static Map<String, Integer> alertLevelMap;

    /**
     * 预警类型列表Map
     * key为表中name，value为表中id
     */
    public static Map<String, Integer> alertTypeMap;
}
