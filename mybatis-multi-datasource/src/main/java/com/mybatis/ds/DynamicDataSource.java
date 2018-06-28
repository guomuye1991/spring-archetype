package com.mybatis.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> THREADLOCAL = new ThreadLocal<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    /**
     * 获取threadLocal中的路由标识
     *
     * @return org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#targetDataSources中的map.key
     */
    @Override
    protected String determineCurrentLookupKey() {
        return THREADLOCAL.get();
    }

    public static void route(String key) {
        LOGGER.info("路由 {}", key);
        THREADLOCAL.set(key);
    }

}
