package com.awu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 搭建zookeeper注册中心的   生产者模块
 */

@SpringBootApplication
@EnableDiscoveryClient //开启服务发现 ,该注解 @EnableDiscoveryClient 用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }


}
