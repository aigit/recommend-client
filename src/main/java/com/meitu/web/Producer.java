package com.meitu.web;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;

public class Producer {

    public static void main(String[] args) {
        Map<String,Object> props = new HashMap<>();
        //  kafka 集群 节点
        props.put("bootstrap.servers", "47.92.109.192:9092");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        String topic = "answer";
        KafkaProducer<String,  String> producer = new KafkaProducer(props);

        producer.send(new ProducerRecord<String, String>(topic, "key", "value-1"));
        producer.send(new ProducerRecord<String, String>(topic, "key", "value-2"));
        producer.send(new ProducerRecord<String, String>(topic, "key", "value-3"));

        producer.close();
    }

}
