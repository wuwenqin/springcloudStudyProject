package com.awu.springcloud.controller;

import com.awu.springcloud.entities.CommonResult;
import com.awu.springcloud.entities.Payment;
import com.awu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/getFeign/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        //通过自己的80的接口层，取调用服务提供者中的接口
        return paymentFeignService.getPaymentById(id);
    }





    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon 客户端一般默认等待1s：就要得到调用的结果。

        return paymentFeignService.paymentFeignTimeout();
    }






}
