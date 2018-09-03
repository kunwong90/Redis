package com.redis.redisson;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author kun.wang
 * @date 2018/9/3
 */
@Component
public class RedisListHelper {

    @Resource
    private RedissonClient redissonClient;

    public boolean push(String key, String... values) {
        RList<String> rList = redissonClient.getList(key);
        return rList.addAll(Arrays.asList(values));
    }

    public int listSize(String key) {
        RList<String> rList = redissonClient.getList(key);
        return rList.size();
    }

    public String lPop(String key) {
        RList<String> rList = redissonClient.getList(key, new StringCodec());
        return rList.remove(0);
    }

    public void lPush(String key, String value) {
        RList<String> rList = redissonClient.getList(key);
        rList.add(0, value);
    }

    public void rPush(String key, String value) {
        RList<String> rList = redissonClient.getList(key);
        rList.add(rList.size(), value);
    }

    /**
     * 获取list指定位置的元素
     * 0表示第一个元素，1表示第二个元素，以此类推
     * -1表示倒数第一个元素，-2表示倒数第二个元素，以此类推
     * @param key
     * @param index
     * @return
     */
    public String get(String key, int index) {
        RList<String> rList = redissonClient.getList(key);
        return rList.get(index);
    }

}
