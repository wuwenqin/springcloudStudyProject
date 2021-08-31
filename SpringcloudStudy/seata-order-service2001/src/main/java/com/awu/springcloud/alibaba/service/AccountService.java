package com.awu.springcloud.alibaba.service;

import com.awu.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 金额 服务层
 */

@FeignClient(value = "seata-account-service")
public interface AccountService {


    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long productId, @RequestParam("money") BigDecimal money);
}

