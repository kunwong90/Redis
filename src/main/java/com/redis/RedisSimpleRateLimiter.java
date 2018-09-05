package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * @author kun.wang
 * @date 2018/9/5
 */
public class RedisSimpleRateLimiter {

    private Jedis jedis;

    public RedisSimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean isActionAllowed(String userId, String actionKey, int period, int mexCount) throws Exception {
        String key = String.format("hist:%s%s", userId, actionKey);
        long now = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        pipeline.zadd(key, now, String.valueOf(now));
        pipeline.zremrangeByScore(key, 0, now - period * 1000);
        Response<Long> count = pipeline.zcard(key);
        pipeline.expire(key, period + 1);
        pipeline.exec();
        pipeline.close();
        return count.get() <= mexCount;
    }

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis();
        RedisSimpleRateLimiter redisSimpleRateLimiter = new RedisSimpleRateLimiter(jedis);
        for (int i = 0; i < 20; i++) {
            System.out.println(redisSimpleRateLimiter.isActionAllowed("laoqian", "reply", 60, 5));
        }
    }
}
