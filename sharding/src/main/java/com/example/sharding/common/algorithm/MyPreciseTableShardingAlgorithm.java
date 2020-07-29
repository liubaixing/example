package com.example.sharding.common.algorithm;

import cn.hutool.setting.dialect.Props;
import com.example.sharding.common.constant.SystemConstant;
import com.example.sharding.common.utils.ShardingUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class MyPreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    // 分表虚拟节点数量
    public static int virtualNodeCount = 1000;

    // 分表虚拟节点范围
    public static String virtualNodeCountRang = "0-332,333-665,666-1000";

    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> preciseShardingValue) {

//        Props props = new Props("application.yml");
//
//        // 初始化分表的虚拟节点数量和范围
//        virtualNodeCount = props.getInt(SystemConstant.SHARDING_TABLE_VIRTUAL_NODE_COUNT);
//        virtualNodeCountRang = props.getStr(SystemConstant.SHARDING_TABLE_VIRTUAL_NODE_COUNT_RANG);

        // 根据用户名的hash值对《virtualNodeCount》进行取余后，得到余数，余数一定在0，《virtualNodeCount》之间的
        Integer mod =  preciseShardingValue.getValue().hashCode() % virtualNodeCount;

        // 由于获取的字符串的hash值可能存在负数，所以需要需要取其绝对值
       mod = ShardingUtils.getAbsoluteValue(mod);

        // 虚拟节点范围映射到实际物理节点
        Integer shardingValue = ShardingUtils.getPhysicNodeByVisualNode(mod, virtualNodeCountRang);

        for (String each : tableNames) {
            // 将余数与配置的实际表名进行匹配
            if (each.endsWith(String.valueOf(shardingValue))) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
