package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.example",
        "com.example.service",
        "com.example.repository"
})
@EnableJpaRepositories(basePackages = {
        "com.example.repository"
})
@EntityScan(basePackages = {
        "com.example.model"
})
public class RecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeApplication.class, args);
        System.out.println("🚀 Recipe API is running successfully...");
    }
}
