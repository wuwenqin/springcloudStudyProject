package com.awu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.awu.springcloud.entities.CommonResult;
import com.awu.springcloud.entities.Payment;
import com.awu.springcloud.handler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @SentinelResource 学习使用
 */

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResourceQWER", blockHandler = "handlerException")    //value中的值对应着Sentinel端口中的资源名
    public CommonResult byResource(){

        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }
    public CommonResult handlerException(BlockException e){
        return new CommonResult(404, e.getClass().getCanonicalName()+"\t 服务不可用");
    }



    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200, "按照URL限流测试OK", new Payment(2020L, "serial002"));
    }







    /**
     * 测试全局的自定义限流处理方法：指定自定义限流处理类和其中的方法作为兜底的方法
     * @return
     */
    @GetMapping("/rateLimit/CustomerBlockHandler")
    @SentinelResource(value = "CustomerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult CustomerBlockHandler(){
        return new CommonResult(200, "全局的客户自定义处理", new Payment(2020L, "serial003"));

    }

}

