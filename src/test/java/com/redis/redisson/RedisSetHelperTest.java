package com.redis.redisson;

import com.redis.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;



public class RedisSetHelperTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSetHelperTest.class);

    @Resource
    private RedisSetHelper redisSetHelper;
    @Test
    public void add() {
        redisSetHelper.add("testset", "dd", "hello", "set");
    }

    @Test
    public void size() {
        int size = redisSetHelper.size("testset");
        LOGGER.info("size result = {}", size);
    }

    @Test
    public void members() {
        Set<String> strings = redisSetHelper.members("testset");
        LOGGER.info("members result = {}", strings);
    }

    @Test
    public void sPop() {
        String result = redisSetHelper.sPop("testset");
        LOGGER.info("sPop result = {}", result);
    }

    @Test
    public void union() {
        Set<String> sets = redisSetHelper.union("set1", "test1set");
        LOGGER.info("union result = {}", sets);
    }


    @Test
    public void unionStore() {
        int count = redisSetHelper.unionStore("descSet", "set", "testset");
        LOGGER.info("unionStore result = {}", count);
    }

    @Test
    public void diff() {
        int result = redisSetHelper.diff("testset");
        LOGGER.info("diff result = {}", result);
    }
}