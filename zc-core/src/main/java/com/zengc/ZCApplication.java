package com.zengc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zengc")
public class ZCApplication {
    public static void main(String[] args) {
        run(args);
    }

    public static void run(String[] args) {
        SpringApplication.run(ZCApplication.class, args);
    }

}

