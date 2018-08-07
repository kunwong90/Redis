package com.redis.service.impl;

import com.redis.annotation.RedisLock;
import com.redis.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class StudentServiceImpl implements IStudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @RedisLock(key = "key")
    @Override
    public int update() {
        LOGGER.info("update start");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }
        LOGGER.info("update end");
        return 1;
    }


}
