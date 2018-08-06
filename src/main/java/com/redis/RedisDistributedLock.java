package com.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDistributedLock {

    @Autowired
    private RedissonClient redissonClient;



    public boolean tryLock(String key) {
        return tryLock(key, 2, TimeUnit.SECONDS);
    }

    public boolean tryLock(String key, long time, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(time, timeUnit);
        } catch (InterruptedException e) {
            return false;
        }
    }
}
