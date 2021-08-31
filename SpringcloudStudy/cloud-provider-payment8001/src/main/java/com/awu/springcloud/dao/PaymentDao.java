package com.awu.springcloud.dao;

import com.awu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 支付模块  Dao层
 * @Repository不用Spring的
 */
@Mapper
public interface PaymentDao {
    //创建订单
    public int create(Payment payment);
    //获取订单ID
    public Payment getPaymentById(@Param("id") Long id);
}

