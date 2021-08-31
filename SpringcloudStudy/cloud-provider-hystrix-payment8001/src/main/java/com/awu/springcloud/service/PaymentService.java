package com.awu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //正常访问，肯定OK的方法
    public String paymentInfo_OK(Integer id){
        return "线程池：  "+ Thread.currentThread().getName()+"   paymentInfo_OK,  id:  "+id
                +"\t"+"O(∩_∩)O哈哈~";
    }

    //模拟拥堵的情况，这属于在高并发的情况，而不是实际代码

    /**
     * 这里模拟的是服务提供方出异常(比如说，用户量大，导致 服务器响应跟不上甚至服务器宕机)
     *   规定这个线程的超时时间是3s，3s后就由fallbackMethod指定的方法帮我“兜底”（服务降级）
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
                    value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 5;
        //int age=10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);   //休眠一段时间(5s)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  "+ Thread.currentThread().getName()+"   paymentInfo_Timeout,  id:  "+id
                +"\t"+"╭(╯^╰)╮" + "耗时"+timeNumber+"秒钟";
    }

    //超时的异常处理
    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：  "+ Thread.currentThread().getName()+"  系统繁忙请稍后再试,  id:  "+id
                +"\t"+"o(╥﹏╥)o";
    }




    //==================服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            ////当在配置时间窗口内达到此数量的失败后，进行短路。10个/10s 注意分母是10s。 默认20个/10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //短路多久以后开始尝试是否恢复，默认5s
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号： "+serialNumber;
    }
    //服务降级方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试， o(╥﹏╥)o  id: "+id;
    }









}

