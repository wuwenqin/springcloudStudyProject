package com.awu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 作为生产者进行   Stream发送消息模块
 *
 * 搭配cloud-stream-rabbitmq-consumer8802 消息接收模块
 * cloud-stream-rabbitmq-consumer8803 消息接收模块
 */



@SpringBootApplication
@EnableEurekaClient
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class,args);
    }

}
