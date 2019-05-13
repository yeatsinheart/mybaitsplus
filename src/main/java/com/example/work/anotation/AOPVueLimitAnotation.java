package com.example.work.anotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class AOPVueLimitAnotation {



    /**
     * 定义切入点，使用了 @RedisCache 的方法
     */
    @Pointcut("@annotation(VueLimitAnotation)")
    public void redisServicePoint(){

    }

    /**
     * 环绕通知，方法拦截器
     */
    @Around("redisServicePoint()")
    public Object WriteReadFromRedis(ProceedingJoinPoint point){
        log.info("<====== 进入 redisCache 环绕通知 ======>");
        /*try {
            // 获取RedisCache注解
            RedisCache redisCache = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(RedisCache.class);
            if(redisCache != null && redisCache.read()){
                // 查询操作
                Object obj = redisTemplate.opsForValue().get(redisCache.key());
                if(obj == null){
                    // Redis 中不存在，则从数据库中查找，并保存到 Redis
                    log.info("<====== Redis 中不存在该记录，从数据库查找 ======>");
                    obj = point.proceed();
                    if(obj != null) {
                        *//*if(redisCache.expired() > 0) {
                            redisTemplate.opsForValue().set(redisCache.key(), obj, redisCache.expired(), TimeUnit.SECONDS);
                        }else {
                            redisTemplate.opsForValue().set(redisCache.key(), obj);
                        }*//*
                    }
                }

                return obj;
            }
        }catch (Throwable ex){
            logger.error("<====== RedisCache 执行异常: {} ======>", ex);
        }*/
        log.info("<====== 退出 redisCache 环绕通知 ======>");
        return null;
    }
}

