package com.example.inix.infrastructure.adapter;

import com.example.inix.domain.model.DataRS;
import com.example.inix.domain.port.IPriceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PriceCrudRepositoryImpl implements IPriceRepository {
    @Override
    public DataRS findByDateProductBrand(String date, Integer productId, Integer brandId) {
        return null;
    }
}
