package com.redis.redisson;

import org.redisson.api.RKeys;
import org.redisson.api.RType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author kun.wang
 * @date 2018/9/3
 */
@Component
public class RedisKeyHelper {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 批量删除key
     * @param keys
     * @return
     */
    public long delete(String... keys) {
        RKeys rKeys = redissonClient.getKeys();
        return rKeys.delete(keys);
    }


    /**
     * 持久化指定key
     * @param key
     * @return
     */
    public boolean persist(String key) {
        RKeys rKeys = redissonClient.getKeys();
        return rKeys.clearExpire(key);
    }

    /**
     * 设置指定key的过期时间
     * @param name
     * @param timeToLive
     * @param timeUnit
     * @return
     */
    public boolean expire(String name, long timeToLive, TimeUnit timeUnit) {
        RKeys rKeys = redissonClient.getKeys();
        return rKeys.expire(name, timeToLive, timeUnit);
    }

    /**
     *
     * @param name key的名字
     * @param timestamp 单位毫秒
     * @return
     */
    public boolean expireAt(String name, long timestamp) {
        RKeys rKeys = redissonClient.getKeys();
        return rKeys.expireAt(name, timestamp);
    }

    public String getObjectType(String key) {
        RKeys rKeys = redissonClient.getKeys();
        RType rType = rKeys.getType(key);
        if (RType.OBJECT.equals(rType)) {
            return "string";
        } else if (RType.LIST.equals(rType)) {
            return "list";
        } else if (RType.SET.equals(rType)) {
            return "set";
        } else if (RType.ZSET.equals(rType)) {
            return "zset";
        } else if (RType.MAP.equals(rType)) {
            return "map";
        } else {
            return "";
        }
    }

    public boolean containsKey(String key) {
        RKeys rKeys = redissonClient.getKeys();
        long count = rKeys.countExists(key);
        return count == 1;
    }

}
