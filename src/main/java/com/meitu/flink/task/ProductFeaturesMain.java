package com.meitu.flink.task;

import com.meitu.config.StaticPropsContext;
import com.meitu.flink.function.LogActionFactoryMap;
import com.meitu.flink.function.ProductFeatureFunction;
import com.meitu.flink.function.UserInterestingFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ProductFeaturesMain {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
                .setBootstrapServers(StaticPropsContext.getKafkaProperties().getBootstrapServers())
                .setTopics("product-feature")
                .setGroupId("product")
                .setStartingOffsets(OffsetsInitializer.latest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();

        env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "Kafka Source")
                .map(new LogActionFactoryMap())
                .map(new ProductFeatureFunction())
                .map(new UserInterestingFunction());
        env.execute("ProductFeaturesMain");

    }
}
