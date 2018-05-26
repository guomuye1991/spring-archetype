# Spring-WebFlux
>`No-Blocking Server`+`Reactive`
>   1. 友好支持lambda作为Operators进行表示性开发
>   1. 性能方面理论上是优于<=Servlet3.0版本，但是基于Servlet3.1版本MVC已经使用了非阻塞IO
>       * [可参考](https://medium.com/@the.raj.saxena/springboot-2-performance-servlet-stack-vs-webflux-reactive-stack-528ad5e9dadc)
>   1. 扩展方面不仅支持Servlet容器，还支持Netty这种非Servlet容器
>   1. 开发方面都可采用Spring-Web模块的注解，所以迁移起来666
>   1. 想要看MVC和WebFlux区别的分别参考`DispatcherServlet`和`DispatcherHandler`
>       * [官方地址拿好](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux)
        * 这里终于不会吐槽了，以往的Spring文档就是大白页，最近的排版棒棒哒
>   1. 想要深入了解Reactive的可以参考[ReactiveX](http://reactivex.io/)
>       1. 一句话总结响应式编程`对数据流变化做出反应`
>       1. 采用升级版观察者模式：Observable+Operators+Observers
        1. Operators对Lambda的支持非常友好
>    1. 最后补一句服务端想要完全`Reactive`，必须得找齐一套`Reactive`组件
## 包说明
>anno声明式  
>func编程式