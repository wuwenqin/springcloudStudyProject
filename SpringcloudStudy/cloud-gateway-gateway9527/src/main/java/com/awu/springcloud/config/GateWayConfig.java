package com.awu.springcloud.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        //构建一个路由器
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        //路由器的id是：path_route_awu，规则是我现在访问/guonei，将会转发到http://news.baidu.com/guonei
        routes.route("path_route_awu",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}

