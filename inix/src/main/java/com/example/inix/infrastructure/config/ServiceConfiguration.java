package com.example.inix.infrastructure.config;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.port.IPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public PriceService priceService(IPriceRepository iPriceRepository) {
        return new PriceService(iPriceRepository);
    }
}
