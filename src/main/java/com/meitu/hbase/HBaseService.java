package com.meitu.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.*;

public class HBaseService {


    public static void putData(String tableName,String rowKey,String columnFamily,String column,String value) throws Exception {
        try(Table table = HBaseConfig.connection.getTable(TableName.valueOf(tableName))){
            Put put = new Put(rowKey.getBytes());
            put.addColumn(columnFamily.getBytes(), column.getBytes(), value.getBytes());
            table.put(put);
        }
    }

    public static String getData(String tableName,String rowKey,String columnFamily,String column) throws Exception {
        try(Table table = HBaseConfig.connection.getTable(TableName.valueOf(tableName))){
            Get get = new Get(rowKey.getBytes());
            Result result = table.get(get);
            byte[] value = result.getValue(columnFamily.getBytes(), column.getBytes());
            return (value != null) ? new String(value) : null;
        }
    }

    public static List<Map.Entry<String, Double>> getRow(String tableName,String rowKey) throws Exception {
        try(Table table = HBaseConfig.connection.getTable(TableName.valueOf(tableName))){
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

    public static void increament(String tableName,String rowKey,String columnFamily,String column) throws Exception {
        try(Table table = HBaseConfig.connection.getTable(TableName.valueOf(tableName))){
            String val = getData(tableName, rowKey, columnFamily, column);
            int res = 1;
            if (val != null) {
                res = Integer.parseInt(val) + 1;
            }

           putData(tableName, rowKey, columnFamily, column, String.valueOf(res));
        }
    }

}
