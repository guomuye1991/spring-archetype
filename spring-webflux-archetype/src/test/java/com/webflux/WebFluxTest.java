package com.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebFluxTest {

    public static void main(String[] args) throws InterruptedException {
        WebClient client = WebClient.create("http://localhost:8080");

        Mono<ClientResponse> result = client.get()
                .uri("/anno/hello?str=gmy")
                .accept(MediaType.TEXT_PLAIN)
                .exchange();

        result.flatMap(res -> res.bodyToMono(String.class)).subscribe(e -> System.err.println("订阅者one " + e.intern()));
        result.flatMap(res -> res.bodyToMono(String.class)).subscribe(e -> System.err.println("订阅者two " + e.intern()));
        Thread.sleep(10000);
        System.out.println("end");
    }

}
