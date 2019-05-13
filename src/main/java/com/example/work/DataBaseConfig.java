package com.example.work;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@MapperScan("com.example.work.*.mapper")
public class DataBaseConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() { //分页插件
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql"); //指定数据库类型
        return page;

    }
}
