package com.meitu.flink.function;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.meitu.domain.LogEntity;
import com.meitu.hbase.HBaseService;
import org.apache.flink.api.common.functions.MapFunction;

public class ProductFeatureFunction implements MapFunction<LogEntity,LogEntity> {
    @Override
    public LogEntity map(LogEntity logEntity) throws Exception {
        try {
            HBaseService hbaseClient = SpringUtil.getBean(HBaseService.class);
            hbaseClient.increament("p_history",String.valueOf(logEntity.getProductId()),"u",String.valueOf(logEntity.getUserId()));
            return logEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
