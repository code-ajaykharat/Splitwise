package com.splitwise.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //step-1
public class SplitwiseApplication{
    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }
}
