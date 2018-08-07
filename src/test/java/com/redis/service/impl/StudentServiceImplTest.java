package com.redis.service.impl;

import com.redis.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml"})
public class StudentServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImplTest.class);

    @Autowired
    private IStudentService studentService;

    @Test
    public void update() {
        int result = studentService.update();
        LOGGER.info("update result = {}", result);
    }

    @Test
    public void update1() {
        int result = studentService.update();
        LOGGER.info("update result = {}", result);
    }
}