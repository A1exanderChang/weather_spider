package com.ebs.weather_spider.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ebs.weather_spider.config.GlobalVariables;
import com.ebs.weather_spider.dto.*;
import com.ebs.weather_spider.entity.WeatherAlert;
import com.ebs.weather_spider.entity.WeatherCity;
import com.ebs.weather_spider.entity.WeatherRecord;
import com.ebs.weather_spider.mapper.WeatherAlertMapper;
import com.ebs.weather_spider.mapper.WeatherCityMapper;
import com.ebs.weather_spider.mapper.WeatherRecordMapper;
import com.ebs.weather_spider.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
public class SpiderServiceImpl implements SpiderService {
    @Autowired
    private HttpHeader httpHeader;
    @Autowired
    private WeatherCityMapper weatherCityMapper;
    @Autowired
    private WeatherRecordMapper weatherRecordMapper;
    @Autowired
    private WeatherAlertMapper weatherAlertMapper;

    @Value("${url.weather-city-url}")
    private String weatherURL;
    @Value("${url.weather-alert-url}")
    private String alarmURL;
    @Value("${url.weather-alert-detail-url}")
    private String alarmDetailURL;
    @Value("${param.province}")
    private String province;
    @Value("${param.page-no}")
    private String pageNo;
    @Value("${param.page-size}")
    private String pageSize;

    @Override
    public void weatherCitySpider(WeatherCity weatherCity) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(weatherURL);
        uriBuilder.setParameter("stationid", weatherCity.getCode());
        uriBuilder.setParameter("_", String.valueOf(System.currentTimeMillis()));
        /**
         * 创建HttpGet连接实例，并传入目标地址
         */
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        /**
         * 设置请求头
         */
        httpHeader.getHeader().forEach((key, value) -> httpGet.setHeader(key, value));
        /**
         * 执行请求
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != 200) {
            log.error("城市天气-" + weatherCity.getName() + "请求失败！");
            return;
        }
        /**
         * 获取响应的json
         */
        HttpEntity httpEntity = response.getEntity();
        /**
         * 对json进行反序列化
         */
        NMCWeatherResponseEntityJSONDTO nmcWeatherResponseEntityJSONDTO = JSONUtil.toBean(EntityUtils.toString(httpEntity), NMCWeatherResponseEntityJSONDTO.class);
        NMCWeatherResponseEntityJSONRealDTO nmcWeatherResponseEntityJSONRealDTO = nmcWeatherResponseEntityJSONDTO.getData().getReal();
        /**
         * 将数据持久化
         */
        /**
         * 保存到城市天气表
         */
        LambdaUpdateWrapper<WeatherCity> weatherCityLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        weatherCityLambdaUpdateWrapper
                .eq(WeatherCity::getId, weatherCity.getId())
                .set(WeatherCity::getTemperature, nmcWeatherResponseEntityJSONRealDTO.getWeather().getTemperature())
                .set(WeatherCity::getHumidity, nmcWeatherResponseEntityJSONRealDTO.getWeather().getHumidity())
                .set(WeatherCity::getRain, nmcWeatherResponseEntityJSONRealDTO.getWeather().getRain())
                .set(WeatherCity::getFeelst, nmcWeatherResponseEntityJSONRealDTO.getWeather().getFeelst())
                .set(WeatherCity::getInfo, nmcWeatherResponseEntityJSONRealDTO.getWeather().getInfo())
                .set(WeatherCity::getWind_direct, nmcWeatherResponseEntityJSONRealDTO.getWind().getDirect())
                .set(WeatherCity::getWind_power, nmcWeatherResponseEntityJSONRealDTO.getWind().getPower())
                .set(WeatherCity::getWind_speed, nmcWeatherResponseEntityJSONRealDTO.getWind().getSpeed())
                .set(WeatherCity::getPublish_time, nmcWeatherResponseEntityJSONRealDTO.getPublish_time());
        weatherCityMapper.update(null, weatherCityLambdaUpdateWrapper);
        /**
         * 保存天气记录
         */
        WeatherRecord weatherRecord = new WeatherRecord();
        BeanUtils.copyProperties(weatherCity, weatherRecord);
        weatherRecord.setId(null);
        weatherRecord.setTemperature(nmcWeatherResponseEntityJSONRealDTO.getWeather().getTemperature());
        weatherRecord.setHumidity(nmcWeatherResponseEntityJSONRealDTO.getWeather().getHumidity());
        weatherRecord.setRain(nmcWeatherResponseEntityJSONRealDTO.getWeather().getRain());
        weatherRecord.setFeelst(nmcWeatherResponseEntityJSONRealDTO.getWeather().getFeelst());
        weatherRecord.setInfo(nmcWeatherResponseEntityJSONRealDTO.getWeather().getInfo());
        weatherRecord.setWind_direct(nmcWeatherResponseEntityJSONRealDTO.getWind().getDirect());
        weatherRecord.setWind_power(nmcWeatherResponseEntityJSONRealDTO.getWind().getPower());
        weatherRecord.setWind_speed(nmcWeatherResponseEntityJSONRealDTO.getWind().getSpeed());
        weatherRecord.setPublish_time(nmcWeatherResponseEntityJSONRealDTO.getPublish_time());
        weatherRecordMapper.insert(weatherRecord);
    }

    @Override
    public void weatherAlertSpider() throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(alarmURL);
        uriBuilder.setParameter("pageNo", pageNo);
        uriBuilder.setParameter("pageSize", pageSize);
        uriBuilder.setParameter("signaltype", "");
        uriBuilder.setParameter("signallevel", "");
        uriBuilder.setParameter("province", province);
        uriBuilder.setParameter("_", String.valueOf(System.currentTimeMillis()));
        // 创建HttpGet连接实例，并传入目标地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        // 设置请求头
        httpHeader.getHeader().forEach((key, value) -> httpGet.setHeader(key, value));
        // 执行请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != 200) {
            log.error("气象预警请求失败！");
            return;
        }
        /**
         * 获取响应的json
         */
        HttpEntity httpEntity = response.getEntity();
        /**
         * 对json进行反序列化
         */
        NMCAlertResponseEntityJSONDTO nmcAlertResponseEntityJSONDTO = JSONUtil.toBean(EntityUtils.toString(httpEntity), NMCAlertResponseEntityJSONDTO.class);
        List<NMCAlertResponseEntityJSONAlertDTO> nmcAlertResponseEntityJSONAlertDTOList = nmcAlertResponseEntityJSONDTO.getData().getPage().getList();
        /**
         * 遍历查询到的预警，将没有收录的预警信息增量保存到`气象预警表`
         * `气象预警表`中，alertid和url均添加了索引，可任选其一作为`指纹`，判断预警信息是否被收录
         */
        for (NMCAlertResponseEntityJSONAlertDTO nmcAlertResponseEntityJSONAlertDTO : nmcAlertResponseEntityJSONAlertDTOList) {
            /**
             * 如果当前预警信息已收录，则跳过
             */
            Long count = new LambdaQueryChainWrapper<>(weatherAlertMapper)
                    .eq(WeatherAlert::getAlertid, nmcAlertResponseEntityJSONAlertDTO.getAlertid())
                    .count();
            if (count > 0) {
                continue;
            }
            /**
             * 当前预警信息未收录，查询详情信息
             */
            Connection connection = Jsoup.connect(alarmDetailURL + nmcAlertResponseEntityJSONAlertDTO.getUrl());
            httpHeader.getHeader().forEach((key, values) -> connection.header(key, values));
            Document document = connection.get();
            Elements content = document.select("#alarmtext");
            WeatherAlert weatherAlert = new WeatherAlert();
            BeanUtils.copyProperties(nmcAlertResponseEntityJSONAlertDTO, weatherAlert);
            weatherAlert.setContent(content.text());
            List<String> keyWordList = GlobalVariables.keywordTree.matchAll(nmcAlertResponseEntityJSONAlertDTO.getTitle(), -1, false, true);
            for (String keyWork : keyWordList) {
                if (keyWork.contains("色")) {
                    weatherAlert.setLevel(GlobalVariables.alertLevelMap.get(keyWork));
                } else {
                    weatherAlert.setType(GlobalVariables.alertTypeMap.get(keyWork));
                }
            }
            weatherAlertMapper.insert(weatherAlert);
        }
    }
}
