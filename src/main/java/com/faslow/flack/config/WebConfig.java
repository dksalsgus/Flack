package com.faslow.flack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")             // mapping path 설정
                .allowedOrigins("http://localhost:8080") // 리소스 공유 허용할 origin 설정
                .allowedMethods("*");           // 허용할 HTTP method 지정
    }

}