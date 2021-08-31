package com.awu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 模块 fegin-hystrix-order80搭配 provider-hystrix-payment8001
 */

@SpringBootApplication
// @EnableEurekaClient 不向Eureka注册了，不是Eureka客户端
@EnableFeignClients //作为Feign客户端
@EnableHystrix    //@EnableHystrix包含了   @EnableCircuitBreaker,开启Hystrix支持
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class, args);
    }
}

