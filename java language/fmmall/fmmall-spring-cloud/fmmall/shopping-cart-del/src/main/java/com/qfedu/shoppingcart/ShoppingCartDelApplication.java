package com.qfedu.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qfedu.shoppingcart.dao")
public class ShoppingCartDelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartDelApplication.class, args);
    }

}
