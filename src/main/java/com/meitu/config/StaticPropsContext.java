package com.meitu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticPropsContext {

    private static KafkaProperties kafkaProperties;

    @Autowired
    public StaticPropsContext(KafkaProperties kafkaProperties) {
        StaticPropsContext.kafkaProperties = kafkaProperties;
    }

    public static KafkaProperties getKafkaProperties() {
        return kafkaProperties;
    }


}
