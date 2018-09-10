package com.redis.redisson;

import com.redis.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class RedisZSetHelperTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisZSetHelperTest.class);

    @Resource
    private RedisZSetHelper redisZSetHelper;

    @Test
    public void zAdd() {
        redisZSetHelper.zAdd("testzset", 10.2, "chinaese");
    }

    @Test
    public void zAddAll() {
        Map<String, Double> map = new HashMap<>();
        map.put("chinases", 98.3);
        map.put("math", 99.0);
        redisZSetHelper.zAddAll("ddd", map);
    }

    @Test
    public void zScore() {
        Double score = redisZSetHelper.zScore("ddd", "math");
        LOGGER.info("zScore result = {}", score);
    }
}