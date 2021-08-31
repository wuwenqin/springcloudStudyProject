package com.awu.springcloud.alibaba.service;

import com.awu.springcloud.alibaba.dao.OrderDao;
import com.awu.springcloud.alibaba.domain.Order;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现层
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    @GlobalTransactional(name = "txl-create-order", rollbackFor = Exception.class)
    public void creat(Order order) {

        log.info("-------->开始新建订单");
        orderDao.create(order);

        log.info("------>订单微服务开始调用库存，进行扣减数量");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("------>订单微服务开始调用账户，进行扣减钱");
        accountService.decrease(order.getUserId(), order.getMoney());

        log.info("------>修改订单的状态：0-->1， 1表示订单已完成");
        orderDao.update(order.getUserId(), 0);

        log.info("--->下订单结束了，O(∩_∩)O哈哈~");
    }
}


