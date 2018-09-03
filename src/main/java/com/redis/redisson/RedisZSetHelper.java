package com.redis.redisson;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author kun.wang
 * @date 2018/9/3
 */
@Component
public class RedisZSetHelper {

    @Resource
    private RedissonClient redissonClient;

    public boolean zAdd(String key, double score, String vlaue) {
        RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
        return rScoredSortedSet.add(score, vlaue);
    }

    public Long zAddAll(String key, Map<String, Double> objects) {
        RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
        return rScoredSortedSet.addAll(objects);
    }

    public Double zScore(String key, String member) {
        RScoredSortedSet<String> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
        return rScoredSortedSet.getScore(member);
    }
}
