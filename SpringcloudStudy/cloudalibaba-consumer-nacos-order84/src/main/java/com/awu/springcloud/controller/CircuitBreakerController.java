package com.awu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.awu.springcloud.entities.CommonResult;
import com.awu.springcloud.entities.Payment;
import com.awu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircuitBreakerController {

    @Value("${server-url.nacos-user-service}")
    private String SERVER_URL;
    //public static final String SERVER_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback" ,fallback = "handlerFallback")       //fallback 只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")     //blockHandler只负责sentinel控制台的违规配置
    @SentinelResource(value = "fallback",fallback = "handlerFallback", blockHandler = "blockHandler")   //配置限流规则：配置fallback和blockHandler
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/"+id, CommonResult.class, id);

        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常....");
        }else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException，该ID没有对应记录，空指针异常");
        }

        return result;
    }
    //handlerFallback：兜底处理异常方法
    public CommonResult handlerFallback(Long id, Throwable e){
        Payment payment = new Payment(id, "null");

        return new CommonResult<>(444, "兜底异常handlerFallback，exception内容 "+e.getMessage(), payment);
    }

    //blockHandler:只负责sentinel控制台的违规配置
    public CommonResult blockHandler(@PathVariable Long id, BlockException e){
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler-sentinel限流，无此流水：blockException " + e.getMessage());
    }





    //===========openFeign   使用feign远程调用
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){

        return paymentService.paymentSQL(id);
    }








}

