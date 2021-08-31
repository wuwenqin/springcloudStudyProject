package com.awu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer implements LoadBalancer {

    //原子类   juc的内容
    private AtomicInteger atomicInteger = new AtomicInteger(0);



    // 自增
    public final int getAndIncrement(){
        int current;
        int next;

        //从下面的分析中得知：该循环主要是得到next值，单机是每循环一次返回一次next
        //高并发时：就不是这种情况了。因为数字会被抢占。
        do{
            current = this.atomicInteger.get();
            //Integer.MAX_VALUE = 2147483647
            next = current >= 2147483647 ? 0 : current+1;

            /**
             * 当atomicInteger=0时，current=0, next=1
             * atomicInteger和current进行比较，相等时返回true将atomicInteger更新为next，1，取反跳出循环，再返回next
             * 当atomicInteger=2147483647时，current=2147483647，next=0
             * atomicInteger和current进行比较，相等时返回true将atomicInteger更新为next, 0，取反跳出循环，再返回next
             *
             * 如果是高并发时
             */
        }while(!this.atomicInteger.compareAndSet(current,next));

        System.out.println("******第几次访问次数next：" + next);

        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //被调用的服务的下标
        int index = getAndIncrement() % serviceInstances.size();
        //被调用的服务
        return serviceInstances.get(index);
    }
}

