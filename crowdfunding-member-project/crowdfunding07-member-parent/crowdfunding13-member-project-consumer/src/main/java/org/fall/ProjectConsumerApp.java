package org.fall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProjectConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProjectConsumerApp.class,args);
    }
}
