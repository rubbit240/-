package org.fall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//开启远程方法调用注解，需要在主启动类添加
@EnableFeignClients
@SpringBootApplication
public class AuthConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthConsumerApp.class, args);
    }
}
