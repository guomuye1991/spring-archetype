package com.webflux.anno;

import com.webflux.handler.HelloHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anno")
public class HelloAnnotationController {



    @RequestMapping("hello")
    public String hello(@RequestParam(required = false) String str) {
        return String.format("Anno Hello %s!",str);
    }

}
