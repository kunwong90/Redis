package com.redis.redisson;

import com.redis.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;



public class RedisListHelperTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisListHelperTest.class);

    @Resource
    private RedisListHelper redisListHelper;
    @Test
    public void push() {
        boolean result = redisListHelper.push("list1", "hello", "world", "nihao");
        LOGGER.info("push result = {}", result);
    }

    @Test
    public void listSize() {
        int size = redisListHelper.listSize("list1");
        LOGGER.info("listSize result = {}", size);
    }

    @Test
    public void lPop() {
        String result = redisListHelper.lPop("course");
        LOGGER.info("lPop result = {}", result);
    }

    @Test
    public void lPush() {
        redisListHelper.lPush("course", "c+++");
    }

    @Test
    public void rPush() {
        redisListHelper.rPush("course", "java");
    }

    @Test
    public void get() {
        String result = redisListHelper.get("course", -10);
        LOGGER.info("get result = {}", result);
    }
}