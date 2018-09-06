package com.redis.aop;

import com.redis.annotation.RedisLock;
import com.redis.redisson.RedisDistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class RedisLockAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockAspect.class);

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @Around("execution(* com.redis.service.impl..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(method.getName(),
                method.getParameterTypes());
        LOGGER.info("method name = {}", realMethod.getName());
        RedisLock annotation = realMethod.getAnnotation(RedisLock.class);
        if (annotation != null) {
            boolean lock = redisDistributedLock.tryLock(annotation.key());
            return proceed(lock, pjp);
        } else {
            LOGGER.info("No RedisLock Annotation!");
        }
        return proceed(false, pjp);
    }

    private Object proceed(boolean lock, ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.info("proceed lock = {}", lock);
        Object result = pjp.proceed();
        if (lock) {
            redisDistributedLock.unlock();
        }
        return result;
    }
}
