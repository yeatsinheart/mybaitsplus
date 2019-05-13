package com.example.work.comon.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class UserSignAOP {
/*    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    JwtProperties jwtProperties;*/

    @Pointcut("@annotation(UserSign)")
    public void signPoint() {

    }

    /*
    *
    注　　解	通　　知
    @After	通知方法会在目标方法返回或抛出异常后调用
    @AfterReturning	通知方法会在目标方法返回后调用
    @AfterThrowing	通知方法会在目标方法抛出异常后调用
    @Around	通知方法会将目标方法封装起来
    @Before	通知方法会在目标方法调用之前执行
    * */
    /*
     *** 环绕切
     * 注意：方法参数第一个为加密字符串：方法(加密字符串，其它参数)
     * */
    @Around("signPoint()")
    public Object doSignAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        //根据用户 token 获取用户信息
        ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //String token = requestAttr.getRequest().getHeader(jwtProperties.getHeader()).substring(7);
        // String desKey = token.substring(token.length() - 16);
        // args[0] = AesEncryptUtil.desEncrypt(args[0].toString(), desKey, desKey);
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("暂不支持非方法注解");
        }
        //实际方法调用
        Object object = joinPoint.proceed(args);
        return object;
    }

    /**
     * 指定拦截器规则；也可直接使用within(@org.springframework.web.bind.annotation.RestController *)
     * 这样简单点 可以通用
     *
     * @param Throwable
     */
    @AfterThrowing(pointcut = "signPoint()", throwing = "e")
    public void afterThrowable(Throwable e) {
        log.error("切面发生了异常：", e);
    }
}