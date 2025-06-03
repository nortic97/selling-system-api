package com.nortic.pos.ts.platforminitializer.application;

import com.nortic.pos.ts.platforminitializer.domain.service.PlatformInitializationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PlatformInitializer implements CommandLineRunner {

    private final PlatformInitializationService platformInitializationService;

    public PlatformInitializer(PlatformInitializationService platformInitializationService) {
        this.platformInitializationService = platformInitializationService;
    }

    @Override
    public void run(String... args) throws Exception {
        platformInitializationService.initializePlatformData();
    }

}
