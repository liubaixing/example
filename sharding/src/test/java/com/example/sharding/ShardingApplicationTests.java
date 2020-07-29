package com.example.sharding;

import cn.hutool.setting.dialect.Props;
import com.example.sharding.common.constant.SystemConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.Properties;

@SpringBootTest
class ShardingApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDB(){

        jdbcTemplate.update("insert into user(name)  values (?) ","测试11");

    }

    @Test
    public void testProps(){
    }

}
