package org.fall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerApp.class,args);
    }
}
