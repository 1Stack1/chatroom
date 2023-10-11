package com.example.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest(classes = DaoApplicationTests.class)
@ComponentScan(basePackages = "com.example.dao")
@MapperScan(basePackages = "com.example.server.mapper")
class DaoApplicationTests {


    /*@Autowired
    FriendService friendService;


    @Test
    void test02() {
        System.out.println(friendService.getById(1));
    }*/
}
