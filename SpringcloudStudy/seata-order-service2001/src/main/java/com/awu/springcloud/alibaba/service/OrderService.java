package com.awu.springcloud.alibaba.service;

import com.awu.springcloud.alibaba.domain.Order;

/**
 * 订单 服务层
 */

//    Order2001驱动自己，外加调用库存和账户：3个service
public interface OrderService {
    void creat(Order order);
}

