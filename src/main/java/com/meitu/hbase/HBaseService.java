package com.meitu.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HBaseService {

    @Resource
    private Connection hbaseConnection;

    public void putData(String tableName,String rowKey,String columnFamily,String column,String value) throws Exception {
        try(Table table = hbaseConnection.getTable(TableName.valueOf(tableName)){
            Put put = new Put(rowKey.getBytes());
            put.addColumn(columnFamily.getBytes(), column.getBytes(), value.getBytes());
            table.put(put);
        }
    }

    public String getData(String tableName,String rowKey,String columnFamily,String column) throws Exception {
        try(Table table = hbaseConnection.getTable(TableName.valueOf(tableName))){
            Get get = new Get(rowKey.getBytes());
            Result result = table.get(get);
            byte[] value = result.getValue(columnFamily.getBytes(), column.getBytes());
            return (value != null) ? new String(value) : null;
        }
    }

}
