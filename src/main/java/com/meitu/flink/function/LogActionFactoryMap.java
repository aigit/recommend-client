package com.meitu.flink.function;

import cn.hutool.json.JSONUtil;
import com.meitu.domain.LogEntity;
import org.apache.flink.api.common.functions.MapFunction;

public class LogActionFactoryMap implements MapFunction<String, LogEntity> {

    @Override
    public LogEntity map(String s) throws Exception {
        return JSONUtil.toBean(s, LogEntity.class);
    }
}
