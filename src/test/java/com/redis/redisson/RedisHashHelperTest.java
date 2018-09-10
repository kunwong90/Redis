package com.redis.redisson;

import com.redis.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


public class RedisHashHelperTest extends BaseTest {

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