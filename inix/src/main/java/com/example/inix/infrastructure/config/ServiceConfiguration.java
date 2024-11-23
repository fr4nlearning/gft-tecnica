package com.example.inix.infrastructure.config;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.port.IPriceRepository;
import com.example.inix.domain.port.IPriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public IPriceService priceService(IPriceRepository iPriceRepository) {
        return new PriceService(iPriceRepository);
    }
}
