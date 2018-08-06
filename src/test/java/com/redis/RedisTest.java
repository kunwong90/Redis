package com.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void test() {
        //for (int i = 0; i < 100000000; i++) {
        //redisTemplate.boundValueOps("key" + i).append("value" + i);
        //}
        //Set<String> key = redisTemplate.keys("*");
        //key.stream().sorted(String::compareTo).forEach(s -> System.out.println(s));

        Set<Object> keys = redisTemplate.execute(new RedisCallback<Set<Object>>() {
            @Override
            public Set<Object> doInRedis(RedisConnection connection) throws DataAccessException {

                Set<Object> binaryKeys = new HashSet<>();
                Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match("key*").count(10).build());
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next()));
                }
                return binaryKeys;
            }
        });

        System.out.println(keys.size());
        //keys.stream().forEach(s -> System.out.println(s));


    }
}