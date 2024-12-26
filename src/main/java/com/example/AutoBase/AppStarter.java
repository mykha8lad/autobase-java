package com.example.AutoBase;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class AppStarter {

    @Bean
    public ApplicationRunner init() {
        log.info("ApplicationRunner has started");
        return args -> {
            System.out.println("-".repeat(20));
        };
    }
}
