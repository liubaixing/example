package com.example.sharding.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConstant {

    /** 分表的虚拟节点数量 **/
    @Value("sharding.table.virtual.node.count")
    public static final String SHARDING_TABLE_VIRTUAL_NODE_COUNT = "sharding.table.virtual.node.count";

    /** 分表的虚拟节点范围 **/
    public static final String SHARDING_TABLE_VIRTUAL_NODE_COUNT_RANG = "sharding.table.virtual.node.rang";

    /** 分割符 **/
    public static final String _DEFAULT_SEPARATOR_COMMA = ",";
}
