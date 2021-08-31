package com.awu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.awu.springcloud.entities.CommonResult;

//创建CustomerBlockHandler类用于自定义限流处理逻辑： 提示一个4444异常
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(4444, "全局GLOBAL的客户自定义处理---1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(4444, "全局GLOBAL的客户自定义处理----2");
    }
}
