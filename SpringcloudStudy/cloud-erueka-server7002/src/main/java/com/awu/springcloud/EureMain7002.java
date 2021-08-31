package com.awu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * Eureka 注册中心  服务端
 */
@SpringBootApplication
@EnableEurekaServer
public class EureMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EureMain7002.class,args);
    }


}
