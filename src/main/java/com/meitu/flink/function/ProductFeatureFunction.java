package com.meitu.flink.function;

import cn.hutool.json.JSONUtil;
import com.meitu.domain.LogEntity;
import org.apache.flink.api.common.functions.MapFunction;

public class ProductFeatureFunction implements MapFunction<String,String> {
    @Override
    public String map(String s) throws Exception {
        try {
            LogEntity logEntity = JSONUtil.toBean(s, LogEntity.class);
            return logEntity.getAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
