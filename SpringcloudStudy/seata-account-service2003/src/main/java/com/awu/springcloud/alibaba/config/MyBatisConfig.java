package com.awu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.awu.springcloud.alibaba.dao"})
public class MyBatisConfig {
}

