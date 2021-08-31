package com.awu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  这里以 Feign 作为远程交互(有默认的负载均衡包)，就不需要使用 Ribbon+restTemplate进行客户端服务调用和负载均衡了。
 *
 *      先启动2个Eureka集群 7001/7002
 *     再启动2个微服务8001/8002
 *     启动使用OpenFeign的80
 *
 */

@SpringBootApplication
@EnableFeignClients     //不作为Eureka客户端了，而是作为Feign客户端
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}

