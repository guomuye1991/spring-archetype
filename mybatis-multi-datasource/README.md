# Mybatis多数据源处理
>多数据源切换

## 静态绑定dao
项目初始化时指定dao的sessionFactory
* 案例：做数据分析项目，需要从很多的ds获取数据
* 机制：`@MapperScan(basePackages = xxx, sqlSessionFactoryRef = yyy)` 为扫描生成的代理dao指定sessionFactory(编码也成)
* 坑点：不建议自己实现`MapperScannerConfigurer`：该类实现了`BeanDefinitionRegistryPostProcessor`，初始化阶段非常的早，可能导致结合spring其它功能时造成初始化顺序依赖问题
## 动态绑定dao
项目运行时根据自定义规则切换dao的sessionFactory
* 案例：针对同样的表进行分库，此时需要根据表规则路由到指定ds(静态绑定需要为每一个ds配置创建dao，采用动态绑定后所有ds可以复用dao)
* 机制：mybatis拦截器+`ThreadLocal(桥梁)`+spring.AbstractRoutingDataSource
* 待完善：对于事务，分布式事务等还需完善