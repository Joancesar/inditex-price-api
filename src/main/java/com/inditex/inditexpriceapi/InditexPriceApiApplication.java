package com.inditex.inditexpriceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InditexPriceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InditexPriceApiApplication.class, args);
    }

}
