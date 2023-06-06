package com.ebs.weather_spider.service.impl;

import cn.hutool.dfa.WordTree;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ebs.weather_spider.config.GlobalVariables;
import com.ebs.weather_spider.entity.AlertLevel;
import com.ebs.weather_spider.entity.AlertType;
import com.ebs.weather_spider.mapper.AlertLevelMapper;
import com.ebs.weather_spider.mapper.AlertTypeMapper;
import com.ebs.weather_spider.service.KeywordTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordTreeServiceImpl implements KeywordTreeService {
    @Autowired
    private AlertLevelMapper alertLevelMapper;
    @Autowired
    private AlertTypeMapper alertTypeMapper;

    @Override
    public void keywordTreeInit() {
        List<AlertLevel> alertLevelList = new LambdaQueryChainWrapper<>(alertLevelMapper).list();
        List<AlertType> alertTypeList = new LambdaQueryChainWrapper<>(alertTypeMapper).list();
        WordTree wordTree = new WordTree();
        alertLevelList.forEach(alertLevel -> wordTree.addWord(alertLevel.getName()));
        alertTypeList.forEach(alertType -> wordTree.addWord(alertType.getName()));
        GlobalVariables.keywordTree = wordTree;
    }
}
