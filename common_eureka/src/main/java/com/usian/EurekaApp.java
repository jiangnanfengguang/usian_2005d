package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApp{

    public static void main(String[] args) {
        System.out.println("增加了一点内容3333");
        System.out.println("我又增加了一点内容-002");
        System.out.println("增加了一点内容2222");
        System.out.println("我又增加了--001");
        SpringApplication.run(EurekaApp.class, args);
    }
}