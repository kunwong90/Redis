package com.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);

    @Autowired
    private RedissonClient redissonClient;

    private ThreadLocal<RLock> lockThreadLocal = new ThreadLocal<>();


    /**
     * 尝试获取锁，默认持有时间5s，5s后锁自动释放
     * @param key
     * @return
     */
    public boolean tryLock(String key) {
        return tryLock(key, 5, TimeUnit.SECONDS);
    }

    public boolean tryLock(String key, long time, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            boolean result = lock.tryLock(0, time, timeUnit);
            if (result) {
                lockThreadLocal.set(lock);
            }
            return result;
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        RLock lock = lockThreadLocal.get();
        if (lock != null && lock.isLocked()) {
            lock.unlock();
        }
    }
}
