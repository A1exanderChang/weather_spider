package com.ebs.weather_spider.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.ebs.weather_spider.mapper.**", sqlSessionFactoryRef = "MySQLDataBaseSessionFactory")
public class MySQLDataBaseConfig {
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;
    @Value("${mysql.driver-class-name}")
    private String driverClassName;
    @Value("${mysql.initial-size}")
    private int ininitialSize;
    @Value("${mysql.min-idle}")
    private int minIdle;
    @Value("${mysql.max-active}")
    private int maxActive;
    @Value("${mysql.max-wati}")
    private long maxWati;
    @Value("${mysql.time-between-eviction-runs-millis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${mysql.min-evictable-idle-time-millis}")
    private long minEvictableIdleTimeMillis;
    @Value("${mysql.validation-query}")
    private String validationQuery;
    @Value("${mysql.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${mysql.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${mysql.test-on-return}")
    private boolean testOnReturn;
    @Value("${mysql.filters}")
    private String filters;

    @Primary
    @Bean("MySQLDataBaseDataSource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 配置基本连接信息
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        // 初始化数量
        druidDataSource.setInitialSize(ininitialSize);
        // 最小数量
        druidDataSource.setMinIdle(minIdle);
        // 最大数量
        druidDataSource.setMaxActive(maxActive);
        // 最大等待时间
        druidDataSource.setMaxWait(maxWati);
        // 配置检测可以关闭的空闲链接间隔时间，单位ms
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 配置连接在池内最小生存时间
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 配置检测连接是否有效
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        // 监控统计拦截的filters
        druidDataSource.setFilters(filters);

        return druidDataSource;
    }

    @Bean("MySQLDataBaseSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("MySQLDataBaseDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        // 其它配置 ps:sqlSessionFactoryBean内可以配置yml的一切配置
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 是否下划线转驼峰
        mybatisConfiguration.setMapUnderscoreToCamelCase(false);
        // 打印日志
        //mybatisConfiguration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);

        // 添加插件
        mybatisSqlSessionFactoryBean.setPlugins(mySQLDataBaseMybatisPlusInterceptor());
        return mybatisSqlSessionFactoryBean.getObject();
    }

    @Bean("MySQLDataBaseMybatisPlusInterceptor")
    public MybatisPlusInterceptor mySQLDataBaseMybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    @Bean("MySQLDataBaseSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("MySQLDataBaseSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("MySQLDataBaseTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("MySQLDataBaseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
