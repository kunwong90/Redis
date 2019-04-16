package com.redis.redisson;

import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

/**
 * @author kun.wang
 * @date 2018/9/3
 */
@Component
public class RedisSetHelper {

    @Resource
    private RedissonClient redissonClient;

    public boolean add(String key, String... values) {
        RSet<String> rSet = redissonClient.getSet(key);
        return rSet.addAll(Arrays.asList(values));
    }

    public int size(String key) {
        RSet<String> rSet = redissonClient.getSet(key);
        return rSet.size();
    }

    public Set<String> members(String key) {
        RSet<String> rSet = redissonClient.getSet(key);
        return rSet.readAll();
    }

    /**
     * 随机返回一个元素并删除
     *
     * @param key
     * @return
     */
    public String sPop(String key) {
        RSet<String> rSet = redissonClient.getSet(key);
        return rSet.removeRandom();
    }

    public Set<String> union(String... keys) {
        RSet<String> rSet = redissonClient.getSet(keys[0]);
        return rSet.readUnion(keys);
    }

    /**
     * 将结果保存到destKey中，并返回destKey集合中的元素数量
     * 如果destKey已存在，则将其覆盖
     *
     * @param destKey
     * @param keys
     * @return
     */
    public int unionStore(String destKey, String... keys) {
        RSet<String> rSet = redissonClient.getSet(destKey);
        return rSet.union(keys);
    }


    /**
     * 返回第n个key不在第n+1个key中元素的数量
     *
     * @param keys
     * @return
     */
    public int diff(String... keys) {
        RSet<String> rSet = redissonClient.getSet(keys[0]);
        return rSet.diff(keys);
    }
}
