package com.awu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  使用Ribbon实现自定义的负载均衡算法。
 */

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){

        return new RandomRule(); //定义为随机

        //定义为随机规则：new RoundRobinRule()
        //定义为重置策略：new RetryRule();
    }
}

