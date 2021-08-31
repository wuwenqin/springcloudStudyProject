package com.awu.springcloud.alibaba.dao;

import com.awu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 与数据库交互 dao层
 */

@Mapper
public interface OrderDao {

    //1.新建订单
    void create(Order order);

    //2.修改订单状态，从0->1
    void update(@Param("userId") Long userId, @Param("status") Integer status);

}
