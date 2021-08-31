package com.awu.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * 学习模块： hystrix 熔断器
 *
 * 当前模块无需引入 数据库依赖，可以使用 exclude 来排除即可
 */

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableCircuitBreaker    //@HystrixCommand注解实现断路器功能，当service方法对应的服务发生异常的时候，会跳转到serviceFallback方法执行
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class, args);
    }



    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关，SpringCloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是“/hystrix.stream”，
     * 只要在自己的项目里配置夏敏的servlet即可。
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

        registrationBean.setLoadOnStartup(1);
        //9001监控8001的监控地址
        registrationBean.addUrlMappings("/hystrix.stream");   //  即指定地址为:   localhost:9001/hystrix.stream
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }




}
