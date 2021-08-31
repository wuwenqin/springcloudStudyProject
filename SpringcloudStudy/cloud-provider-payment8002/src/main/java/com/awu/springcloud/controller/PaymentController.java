package com.awu.springcloud.controller;

import com.awu.springcloud.entities.CommonResult;
import com.awu.springcloud.entities.Payment;
import com.awu.springcloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 支付模块 控制层
 * 类上面添加@Sl4j注解,然后使用log打印日志
 * @Api 可视化API接口
 */

@Api(tags = "支付模块")
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}") //Value绑定单一的属性值
    private String serverPort;


    //传给前端JSON
    @ApiImplicitParam(required = true)
    @ApiOperation(value = "创建支付信息Post")
    @PostMapping(value = "/payment/create")    //写操作POST
    public CommonResult create(@RequestBody Payment payment){
        //由于在mapper.xml配置了useGeneratedKeys="true" keyProperty="id"，会将自增的id封装到实体类中
        int result = paymentService.create(payment);

        log.info("*****插入结果：" + result);
        if(result > 0){
            return new CommonResult(200, "插入数据库成功,serverPort: " +serverPort, result);
        }else {
            return new CommonResult(444,"插入数据库失败", null);
        }
    }

    //传给前端JSON
    @ApiImplicitParam(required = true)
    @ApiOperation(value = "支付获取Id(Get)")
    @GetMapping(value = "/payment/get/{id}")    //读操作GET   通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);

        log.info("*****查询结果：" + payment );

        if(payment != null){
            return new CommonResult(200, "查询数据库成功,serverPort："+serverPort, payment);
        }else {
            return new CommonResult(444,"查询ID:"+id+"没有对应记录", null);
        }
    }





    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }






}
