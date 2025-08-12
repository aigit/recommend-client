package com.meitu.flink.function;

import cn.hutool.extra.spring.SpringUtil;
import com.meitu.domain.LogEntity;
import com.meitu.hbase.HBaseService;
import org.apache.flink.api.common.functions.MapFunction;

public class UserInterestingFunction implements MapFunction<LogEntity,LogEntity> {


    @Override
    public LogEntity map(LogEntity logEntity) throws Exception {
        HBaseService hbaseClient = SpringUtil.getBean(HBaseService.class);
        hbaseClient.increament("u_interest",String.valueOf(logEntity.getUserId()),"p",String.valueOf(logEntity.getProductId()));
        return logEntity;
    }
}
