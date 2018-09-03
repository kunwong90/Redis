package com.redis;

import com.redis.redisson.RedisSetHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml"})
public class RedisSetHelperTest {

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