package com.example.server;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.example.dao.dao")
//@SpringBootApplication(scanBasePackages = {"com.example.server", "com.example.common", "com.example.security", "com.example.dao.dao"})
@SpringBootApplication(scanBasePackages = {"com.example.server", "com.example.common",  "com.example.dao.dao"})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
