package com.awu.springcloud.service;

import com.awu.springcloud.entities.CommonResult;
import com.awu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//我们要到nacos中找nacos-payment-consumer这个微服务，并且指明了接口出错时的兜底方法
@Component
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

        @GetMapping(value = "/paymentSQL/{id}") //去找nacos-payment-consumer服务中的相应接口
        public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);

    }

