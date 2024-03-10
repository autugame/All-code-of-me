package com.qfedu.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
//@EnableScheduling
public class OrderTimeoutCancelApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderTimeoutCancelApplication.class, args);
    }

}
