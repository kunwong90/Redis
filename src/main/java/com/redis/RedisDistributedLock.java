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
     * 如果业务执行时间过长,会导致锁自动释放,其他线程可能会重复获取锁
     * 如果获取锁带有等待时间,一个持有锁的进程崩溃,会导致其他进程一直等待直到获得到锁
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
        // 如果业务执行时间过长导致锁自动释放(key时间过期自动删除),当前线程认为自己当前还持有锁
        if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
            lock.unlock();
            lockThreadLocal.remove();
        }
    }
}
