package com.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml"})
public class RedisDistributedLockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLockTest.class);

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @Test
    public void tryLock() throws Exception {
        try {
            boolean result = redisDistributedLock.tryLock("lock");
            if (result) {
                LOGGER.info("get lock success!");
            } else {
                LOGGER.info("get lock failed!");
            }
        } finally {
            redisDistributedLock.unlock();
        }
    }
}