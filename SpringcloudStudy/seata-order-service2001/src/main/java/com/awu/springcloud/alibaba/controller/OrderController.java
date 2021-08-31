package com.awu.springcloud.alibaba.controller;

import com.awu.springcloud.alibaba.domain.CommonResult;
import com.awu.springcloud.alibaba.domain.Order;
import com.awu.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 调用接口的 控制层
 */

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/order/create")
    public CommonResult create(Order order){
        orderService.creat(order);
        return new CommonResult(200, "订单创建成功");
    }
}

