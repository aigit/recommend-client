package com.meitu.flink;


import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;

public class FlinkDemo {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(FlinkDemo.class);

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> textDs = env.readTextFile("demo.txt");
        FlatMapOperator<String, Tuple2<String, Integer>> flatMapOperator = textDs.flatMap((String i, Collector<Tuple2<String, Integer>> out) -> {
            String[] words = i.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.INT));
        flatMapOperator.groupBy(0).sum(1).print();
    }
}
