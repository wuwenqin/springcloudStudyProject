package com.awu.springcloud.controller;


import com.awu.springcloud.lb.LoadBalancer;
import com.awu.springcloud.entities.CommonResult;
import com.awu.springcloud.entities.Payment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 支付模块 控制层
 * 类上面添加@Sl4j注解,然后使用log打印日志
 * @Api 可视化API接口
 */
@Api(tags = "消费者模块")
@RestController
@Slf4j
public class OrderController {

    //暂时在这里写死地址，后面再重写
//    public static final String PAYMENT_URL="http://localhost:8001";

    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    /**
     * 用restTemplate实现消费者和提供者微服务之间的横向调用
     */
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;


    //按理说插入数据应该是POST，可以从浏览器只能发GET请求，尽管不符合RESTFUL，
    // 但是下面的template.postForObject调用却是POST请求
    /**
     * 调用生产者模块中的生产方法
     *
     * 这里要注意一下：restTemplate是访问Rest服务的客户端模版工具类，这里类似于又发送一个http请求，
     * 但是第二个参数payment对象在传递(http传输)的过程中，肯定要序列化，这里肯定是Json字符串了，
     * 所以在生产者那边根据默认的参数封装规则，封装不进去。
     * --->所以生产者中的接收该参数的方法要加上@RequestBody注解
     */
    @ApiImplicitParam(required = true)
    @ApiOperation(value = "(远程连接8001端口)创建支付信息Post")
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //调用生产者模块中的生产方法
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }


    @ApiImplicitParam(required = true)
    @ApiOperation(value = "(远程连接8001端口)支付获取Id(Get)")
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
     * 测试getForEntity
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "操作失败");
        }
    }



    /**
     * 测试我们自己的轮询算法
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLoadBalancer(){
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }


    /**
     * 测试链路监控
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }




}
