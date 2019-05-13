package com.example.work.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 过滤器：用于属性甄别，对象收集（不可改变过滤对象的属性和行为）
 *
 * 监听器：用于对象监听，行为记录（不可改变监听对象的属性和行为）
 *
 * 拦截器：用于对象拦截，行为干预（可以改变拦截对象的属性和行为）
 *
 *
 * 验证用户会话是否过期
 * Created by eden on 2017/11/21.
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VueLimitAnotation  {
    /**
     * 键
     * @return
     */
    String key() default "";

    /**
     * 过期时间
     * @return
     */
    long expired() default -1;

    /**
     * 是否为查询操作
     * 如果为写入数据库的操作，该值需置为 false
     * @return
     */
    boolean read() default true;

}
