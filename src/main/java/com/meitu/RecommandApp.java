package com.meitu;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@EnableSpringUtil
public class RecommandApp {

    public static void main(String[] args) {
        SpringApplication.run(RecommandApp.class, args);
    }

}
