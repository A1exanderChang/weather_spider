-- 创建`天气爬虫`数据库
drop database if exists weather_spider;
create database if not exists weather_spider default charset = utf8mb4 collate utf8mb4_unicode_ci;
use weather_spider;

-- weather_city，天气城市表
drop table if exists weather_city;
create table if not exists weather_city
(
    id           int auto_increment primary key comment '主键，自增',
    name         varchar(16) not null comment '城市名称',
    province     varchar(16) comment '省份',
    code         varchar(16) comment '中国气象台，城市编号',
    url          varchar(511) comment '中国气象台，城市天气链接',
    temperature  varchar(16) comment '当前气温',
    humidity     varchar(16) comment '相对湿度',
    rain         varchar(16) comment '降水量',
    feelst       varchar(16) comment '体感温度',
    info         varchar(16) comment '天气',
    wind_direct  varchar(16) comment '风向',
    wind_power   varchar(16) comment '风力',
    wind_speed   varchar(16) comment '风速',
    publish_time datetime comment '更新时间'
) comment '天气城市表';
alter table weather_city
    add unique (name);
alter table weather_city
    add unique (code);

-- weather_record，天气记录表
drop table if exists weather_record;
create table if not exists weather_record
(
    id           int auto_increment primary key comment '主键，自增',
    name         varchar(16) not null comment '城市名称',
    province     varchar(16) comment '省份',
    code         varchar(16) comment '中国气象台，城市编号',
    url          varchar(511) comment '中国气象台，城市天气链接',
    temperature  varchar(16) comment '当前气温',
    humidity     varchar(16) comment '相对湿度',
    rain         varchar(16) comment '降水量',
    feelst       varchar(16) comment '体感温度',
    info         varchar(16) comment '天气',
    wind_direct  varchar(16) comment '风向',
    wind_power   varchar(16) comment '风力',
    wind_speed   varchar(16) comment '风速',
    publish_time datetime comment '更新时间',
    gmt_create   datetime    not null default current_timestamp comment '创建时间，必备字段。默认为当前时间，不允许更改。'
) comment '天气记录表';

-- weather_alert，气象预警表
drop table if exists weather_alert;
create table if not exists weather_alert
(
    id        int auto_increment primary key comment '主键，自增',
    alertid   varchar(64) not null comment '预警id',
    title     varchar(255) comment '标题',
    level     int comment '预警等级。关联alert_level表',
    type      int comment '预警类型。关联alert_type表',
    pic       varchar(511) comment '预警图标',
    url       varchar(511) comment '详情页面链接',
    content   text comment '预警文本。需要进入详情页获取',
    issuetime datetime comment '发布时间'
) comment '气象预警表';
alter table weather_alert
    add unique (alertid);
alter table weather_alert
    add unique (url);

-- alert_level，预警等级表
drop table if exists alert_level;
create table if not exists alert_level
(
    id   int auto_increment primary key comment '主键，自增',
    name varchar(16) comment '名称'
) comment '预警等级表';
alter table alert_level
    add unique (name);

insert into alert_level (name)
values ('红色'),
       ('橙色'),
       ('黄色'),
       ('蓝色');

-- alert_type，预警类型表
drop table if exists alert_type;
create table if not exists alert_type
(
    id   int auto_increment primary key comment '主键，自增',
    name varchar(16) comment '名称'
) comment '预警类型表';
alter table alert_type
    add unique (name);
