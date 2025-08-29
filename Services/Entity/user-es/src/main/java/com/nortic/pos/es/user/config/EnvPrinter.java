package com.nortic.pos.es.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnvPrinter implements CommandLineRunner {

    @Value("${variables.env}")
    private String env;

    @Override
    public void run(String... args) {
        log.info("Environment initialized as: {}",env);
    }

}
