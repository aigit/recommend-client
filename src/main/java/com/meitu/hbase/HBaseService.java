package com.meitu.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.com.google.protobuf.MapEntry;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.Resource;
import java.util.*;

@Service
public class HBaseService {

    @Resource
    private Connection hbaseConnection;

    public void putData(String tableName,String rowKey,String columnFamily,String column,String value) throws Exception {
        try(Table table = hbaseConnection.getTable(TableName.valueOf(tableName))){
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

    public List<Map.Entry<String, Double>> getRow(String tableName,String rowKey) throws Exception {
        try(Table table = hbaseConnection.getTable(TableName.valueOf(tableName))){
            Get get = new Get(rowKey.getBytes());
            Result result = table.get(get);

            HashMap<String, Double> rst = new HashMap<>();

            for (Cell cell : result.listCells()) {
                String key = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                rst.put(key, Double.valueOf(value));
            }
            List<Map.Entry<String, Double>> ans = new ArrayList<>();
            ans.addAll(rst.entrySet());

            ans.sort(Comparator.comparing(Map.Entry::getValue));
            return ans;
        }
    }

    public void increament(String tableName,String rowKey,String columnFamily,String column) throws Exception {
        try(Table table = hbaseConnection.getTable(TableName.valueOf(tableName))){
            String val = getData(tableName, rowKey, columnFamily, column);
            int res = 1;
            if (val != null) {
                res = Integer.parseInt(val) + 1;
            }

           putData(tableName, rowKey, columnFamily, column, String.valueOf(res));
        }
    }

}
