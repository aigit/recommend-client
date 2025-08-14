package com.meitu.hbase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HBaseConfig {

    public static Connection connection = null;

    static {
        org.apache.hadoop.conf.Configuration hbaseConf = HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum", "localhost");
        hbaseConf.set("hbase.zookeeper.property.clientPort", "2181");
        hbaseConf.set("hbase.client.operation.timeout", "3000");
        try {
            connection =  ConnectionFactory.createConnection(hbaseConf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
