package com.awu.springcloud.alibaba.service;

import com.awu.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  库存 服务层
 */

@FeignClient(value = "seata-storage-service")  //这里肯定要调用库存微服务
public interface StorageService {

    //比如买了5个1号商品：对1号商品库存减5
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}

