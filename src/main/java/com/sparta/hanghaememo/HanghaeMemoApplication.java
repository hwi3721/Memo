package com.sparta.hanghaememo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeMemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeMemoApplication.class, args);
    }

}
