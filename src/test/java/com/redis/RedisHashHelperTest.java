package com.redis;

import com.redis.redisson.RedisHashHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.naming.Name;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml"})
public class RedisHashHelperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisHashHelperTest.class);

    @Resource
    private RedisHashHelper redisHashHelper;

    @Test
    public void mapSize() {
    }

    @Test
    public void hKeys() {

    }

    @Test
    public void putAll() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "30");
        map.put("sex", "male");
        redisHashHelper.putAll("userinfo", map);
    }

    @Test
    public void getFieldValue() {
        String age = redisHashHelper.getFieldValue("userinfo", "age");
        LOGGER.info("getFieldValue = {}", age);
    }
}