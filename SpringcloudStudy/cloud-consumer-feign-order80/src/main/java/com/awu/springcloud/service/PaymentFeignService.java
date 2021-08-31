package com.awu.springcloud.service;

import com.awu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 这里的服务层service   虽然使用的是@GetMapping 注解(controller层使用的，用来追踪地址)
 * 但其实应该这样理解 ： 使用 Feign 来远程调用 cloud-payment-service模块中的服务(controller层) ，这里需要使用到追踪地址的操作，
 * 然后在调用远程操作后，也可以在  消费者(此模块) 中再另外添加其他的操作(例如日志，切面等等)，所以这里的调用属于service层其实也不足为奇了
 *
 * p.s:简单理解，封装
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //作为一个Feign功能绑定的的接口，这里调用的是 cloud-provider-payment两个模块中的服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

}

