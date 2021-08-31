package com.awu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器
 *
 * @Bean 效果类似于  application.xml   <bean id="" class=""></bean>
 *
 *
 * 配置类 ，远程搭建连接
 */

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //规定一种默认的负载均衡机制     将注解注释，是为了测试自定义的负载均衡算法是否能生效(尚硅谷第42节)
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }



}
