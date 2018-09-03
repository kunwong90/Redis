package com.redis.redisson;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author kun.wang
 * @date 2018/9/3
 */
@Component
public class RedisHashHelper {

    @Resource
    private RedissonClient redissonClient;


    public void putAll(String key, Map<String, String> map) {
        RMapCache<String, String> rMapCache = redissonClient.getMapCache(key);
        rMapCache.putAll(map);
    }

    public String getFieldValue(String key, String field) {
        RMapCache<String, String> rMapCache = redissonClient.getMapCache(key);
        return rMapCache.get(field);
    }
}
