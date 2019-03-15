package com.ziofront.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RestAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestAPIApplication.class, args);
    }

}
