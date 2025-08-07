package com.meitu.hbase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HBaseConfig {

    @Bean
    public Connection hbaseConnection() throws Exception {
        org.apache.hadoop.conf.Configuration hbaseConf = HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum", "47.92.109.192");
        hbaseConf.set("hbase.zookeeper.property.clientPort", "2181");
        hbaseConf.set("hbase.client.operation.timeout", "3000");
        return ConnectionFactory.createConnection(hbaseConf);
    }

}
