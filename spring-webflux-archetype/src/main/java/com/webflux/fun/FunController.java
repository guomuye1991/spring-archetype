package com.webflux.fun;

import com.webflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FunController {



    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/fun/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),e-> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromObject(String.format("Fun Hello %s!",e.queryParam("str").get()))));
    }

}
