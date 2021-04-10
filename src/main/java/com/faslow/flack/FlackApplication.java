package com.faslow.flack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlackApplication.class, args);
    }

}
