//package com.example.work.comon.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//@Configuration
//public class TestInterceptorRegistry implements WebMvcConfigurer {
//    @Resource
//    private TestInterceptor testInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(testInterceptor);
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//}