package com.awu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * nacos 微服务架构     搭配payment9001、9002 使用
 */



@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain83 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain83.class, args);
    }
}

