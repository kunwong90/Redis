package com.redis.redisson;

import com.redis.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisValueHelperTest extends BaseTest {

    @Resource
    private RedisValueHelper redisValueHelper;

    @Test
    public void set() {
        for (int i = 0; i < 10; i++) {
            String key = "key" + i;
            redisValueHelper.set(key, "value" + i);
            System.out.println(redisValueHelper.get(key));
        }
    }
}