package com.example.inix.infrastructure.config;

import com.example.inix.application.services.PriceServiceAdapter;
import com.example.inix.domain.port.PriceServicePort;
import com.example.inix.infrastructure.database.port.PriceCrudRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public PriceServicePort priceService(PriceCrudRepositoryPort priceCrudRepositoryPort) {
        return new PriceServiceAdapter(priceCrudRepositoryPort);
    }
}
