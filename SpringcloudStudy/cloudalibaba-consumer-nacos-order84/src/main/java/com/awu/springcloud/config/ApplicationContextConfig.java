package com.awu.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {


    /**
     * 因为用的Ribbon，所以使用其提供的RestTemplate
     */
    @Bean
    @LoadBalanced    //负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

