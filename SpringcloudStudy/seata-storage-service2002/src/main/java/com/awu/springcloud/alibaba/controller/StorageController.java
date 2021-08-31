package com.awu.springcloud.alibaba.controller;

import com.awu.springcloud.alibaba.domain.CommonResult;
import com.awu.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 库存扣减
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){

        storageService.decrease(productId, count);

        return new CommonResult(200, "扣减库存成功！");
    }
}
