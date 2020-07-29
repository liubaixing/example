package com.example.sharding.common.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class TablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    public static int virtualNodeCount = 1000;

    public static String virtualNodeCountRang ;

    public TablePreciseShardingAlgorithm() {
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        Long mod = preciseShardingValue.getValue() % virtualNodeCount;
        for (String table : collection){
            if(table.endsWith(preciseShardingValue.getValue()%2 + "")){
                return table;
            }
        }
        return null;
    }
}
/*
* tables:
        user:
          actual-data-nodes: ds$->{0..1}.user$->{0..2} #数据节点
          database-strategy:  #分库策略
            standard:
              sharding-column: id
              precise-algorithm-class-name: com.example.sharding.common.sharding.DBPreciseShardingAlgorithm
          tableStrategy:
            standard:
              shardingColumn: user_id
              preciseAlgorithmClassName: com.example.sharding.common.sharding.TablePreciseShardingAlgorithm
          key-generator:
            column: user_id
            type: SNOWFLAKE
*
* */