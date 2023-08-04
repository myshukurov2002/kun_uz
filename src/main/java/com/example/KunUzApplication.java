package com.example;

import com.example.security.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringConfig.class)
public class KunUzApplication {

    public static void main(String[] args) {
        SpringApplication.run(KunUzApplication.class, args);
    }

}
