package com.awu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 *  Hystrix仪表盘 模块，搭配Hystrix
 *  所有的Provider微服务提供类（8001/8002/8003）都需要监控依赖配置
 */


@SpringBootApplication
@EnableHystrixDashboard //开启Hystrix仪表盘
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}

