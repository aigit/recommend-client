package com.meitu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class RecommandApp {

    public static void main(String[] args) {
        SpringApplication.run(RecommandApp.class, args);
    }

}
