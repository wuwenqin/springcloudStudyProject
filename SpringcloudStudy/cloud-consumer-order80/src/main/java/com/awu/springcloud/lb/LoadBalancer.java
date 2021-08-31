package com.awu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义的 负载均衡算法  接口
 */


public interface LoadBalancer {

    //instances()方法：从List<ServiceInstance>得到一个ServiceInstance微服务实例对象
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
