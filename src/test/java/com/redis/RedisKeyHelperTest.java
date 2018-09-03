package com.redis;

import com.redis.redisson.RedisKeyHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.reflect.generics.visitor.Reifier;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml"})
public class RedisKeyHelperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisKeyHelperTest.class);

    @Resource
    private RedisKeyHelper redisKeyHelper;

    @Test
    public void delete() {
        long count = redisKeyHelper.delete("1", "2");
        LOGGER.info("delete count = {}", count);
    }

    @Test
    public void expire() {
        boolean result = redisKeyHelper.expire("key", 2, TimeUnit.MINUTES);
        LOGGER.info("expire result = {}", result);
    }

    @Test
    public void persist() {
        boolean result = redisKeyHelper.persist("key");
        LOGGER.info("persist result = {}", result);
    }

    @Test
    public void expireAt() {
        boolean result = redisKeyHelper.expireAt("key", System.currentTimeMillis() + 60 * 1000);
        LOGGER.info("expireAt result = {}",result);
    }

    @Test
    public void getObjectType() {
        String result = redisKeyHelper.getObjectType("string");
        LOGGER.info("getObjectType result = {}", result);
        result = redisKeyHelper.getObjectType("list");
        LOGGER.info("getObjectType result = {}", result);
        result = redisKeyHelper.getObjectType("set");
        LOGGER.info("getObjectType result = {}", result);
        result = redisKeyHelper.getObjectType("hash");
        LOGGER.info("getObjectType result = {}", result);
        result = redisKeyHelper.getObjectType("zset");
        LOGGER.info("getObjectType result = {}", result);
        result = redisKeyHelper.getObjectType("zset11");
        LOGGER.info("getObjectType result = {}", result);
    }

    @Test
    public void containsKey() {
        boolean result = redisKeyHelper.containsKey("string");
        LOGGER.info("containsKey = {}",result);
        result = redisKeyHelper.containsKey("set");
        LOGGER.info("containsKey = {}",result);
        result = redisKeyHelper.containsKey("zset");
        LOGGER.info("containsKey = {}",result);
        result = redisKeyHelper.containsKey("hash");
        LOGGER.info("containsKey = {}",result);
        result = redisKeyHelper.containsKey("list");
        LOGGER.info("containsKey = {}",result);
        result = redisKeyHelper.containsKey("111");
        LOGGER.info("containsKey = {}",result);
    }
}