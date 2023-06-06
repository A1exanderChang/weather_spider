# 天气爬虫

## 简介

本系统为获取天气信息的爬虫，主要功能为获取[中央气象台](http://www.nmc.cn/)的**天气预报**及**预警信号**信息。

## 天气预报

天气预报信息来源于类似[郑州-天气预报 (nmc.cn)](http://www.nmc.cn/publish/forecast/AHA/zhengzhou.html)页面。

具体的天气信息请求为`http://www.nmc.cn/rest/weather?stationid=57083&_=1685952172396`，其中填写不同的城市编号来获取其城市的天气信息的json。

## 预警信号

预警信息主要来源于`http://www.nmc.cn/publish/alarm.html`请求。

具体预警信息请求为`http://www.nmc.cn/rest/findAlarm?pageNo=1&pageSize=20&signaltype=&signallevel=&province=&_=1685952477576`，其响应结果如下：

```json
{
    "msg": "success",
    "code": 0,
    "data": {
        "page": {
            "pageNo": 1,
            "pageSize": 1,
            "count": 6,
            "prev": 1,
            "next": 2,
            "list": [
                {
                    "alertid": "41062241600000_20230605160643",
                    "issuetime": "2023/06/05 16:06",
                    "title": "河南省鹤壁市淇县气象台发布大风蓝色预警信号",
                    "url": "/publish/alarm/41062241600000_20230605160643.html",
                    "pic": "http://image.nmc.cn/assets/img/alarm/p0007004.png"
                }
            ],
            "totalPage": 6
        },
        "provinceAlarms": [],
        "stat": {
            "city": {
                "b": 0,
                "o": 1,
                "r": 0,
                "y": 1
            },
            "county": {
                "b": 2,
                "o": 0,
                "r": 0,
                "y": 2
            },
            "province": {
                "b": 0,
                "o": 0,
                "r": 0,
                "y": 0
            }
        }
    }
}
```

预警的具体信息可通过`http://www.nmc.cn`+`/publish/alarm/41062241600000_20230605160643.html`进入预警详情信息的页面，通过匹配元素规则获取。