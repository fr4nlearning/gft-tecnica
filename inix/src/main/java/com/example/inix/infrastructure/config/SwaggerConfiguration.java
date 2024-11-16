package com.example.inix.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Prices",
                description = "Provides one endpoint",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "UNIQUE SERVER",
                        url = "/"
                )
        }
)
public class SwaggerConfiguration {
}
