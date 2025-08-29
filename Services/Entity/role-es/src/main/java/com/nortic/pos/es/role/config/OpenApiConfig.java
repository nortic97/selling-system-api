package com.nortic.pos.es.role.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class OpenApiConfig {

    @RestController
    public static class OpenApiController {

        @GetMapping(value = "/openapi-contract.yaml", produces = "application/x-yaml")
        public String getOpenApiContract() throws IOException {
            ClassPathResource resource = new ClassPathResource("openapi-contract.yaml");
            return Files.readString(Paths.get(resource.getURI()), StandardCharsets.UTF_8);
        }

    }

}
