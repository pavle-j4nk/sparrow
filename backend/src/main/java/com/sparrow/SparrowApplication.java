package com.sparrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SparrowApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparrowApplication.class, args);
    }
}
