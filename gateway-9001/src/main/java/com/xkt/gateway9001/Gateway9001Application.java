package com.xkt.gateway9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Gateway9001Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway9001Application.class, args);
    }

}
