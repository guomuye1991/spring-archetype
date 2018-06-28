package com.mybatis.ds.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatis.ds.DynamicDataSource;
import com.mybatis.ds.DynamicDataSourceInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.mybatis.dao.dynamic", sqlSessionFactoryRef = "dynamicSqlSessionFactory")
public class DynamicDatasourceConfig {


    @Bean("dynamic1DataSource")
    @ConfigurationProperties(prefix = "dao.dynamic1.db")
    public DataSource taoBaoDataSource() {
        return new DruidDataSource();
    }

    @Bean("dynamic2DataSource")
    @ConfigurationProperties(prefix = "dao.dynamic2.db")
    public DataSource maoYanDataSource() {
        return new DruidDataSource();
    }

    @Bean("dynamicDataSource")
    public DataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.putIfAbsent("dynamic1", maoYanDataSource());
        targetDataSources.putIfAbsent("dynamic2", taoBaoDataSource());
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }


    @Bean("dynamicSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("dynamicDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dynamic/*.xml"));
        sessionFactory.setPlugins(interceptors());//设置路由拦截器
        return sessionFactory.getObject();
    }

    @Bean
    public Interceptor[] interceptors() {
        return new Interceptor[]{new DynamicDataSourceInterceptor()};
    }

    @Bean("dynamicSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Autowired @Qualifier("dynamicSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
