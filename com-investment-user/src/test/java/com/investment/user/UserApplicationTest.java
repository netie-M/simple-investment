package com.investment.user;

import com.alibaba.fastjson.JSON;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTest {
    @Autowired(required = false)
    DataSourceProperties dataSourceProperties;

    @Autowired(required = false)
    HikariDataSource dataSource;

    /**
     * <p>检查数据库是否配置成功</p>
     */
    @Test
    public void testDataSource(){
        System.out.println("dataSourceProperties == null:"+ (dataSourceProperties == null));
        System.out.println("dataSource == null:"+ (dataSource == null));
        if(dataSource != null){
            System.out.println("dataSource.poolName:" + dataSource.getPoolName());
            System.out.println("dataSource.jdbcUrl:" + dataSource.getJdbcUrl());

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            Map<String,Object> map = jdbcTemplate.queryForMap("select 1");
            System.out.println("query result:" + map);
            List<?> resultList = jdbcTemplate.queryForList("select * from pd_user where id = 51045");
            System.out.println("query result:" + JSON.toJSONString(resultList));
        }
    }
}