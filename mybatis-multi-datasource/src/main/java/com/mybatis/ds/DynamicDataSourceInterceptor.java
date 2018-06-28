package com.mybatis.ds;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 配置需要拦截的操作
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //编写路由逻辑，以下为demo
        Object[] args = invocation.getArgs();
        for (Object arg : args) {
            if ((arg instanceof MapperMethod.ParamMap)) {
                //noinspection ConstantConditions
                DynamicDataSource.route((((MapperMethod.ParamMap) arg)).get("routeParam").toString());
                break;
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor)
            return Plugin.wrap(target, this);
        else
            return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
