package com.example.inix.infrastructure.adapter;

import com.example.inix.domain.model.DataRS;
import com.example.inix.domain.port.IPriceRepository;
import com.example.inix.infrastructure.mapper.DataRSMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PriceCrudRepositoryImpl implements IPriceRepository {

    private final IPriceCrudRepository iPriceCrudRepository;
    private final DataRSMapper dataRSMapper;

    @Override
    public DataRS findByDateProductBrand(String date, Integer productId, Integer brandId) {

        return dataRSMapper.toDataRS(this.iPriceCrudRepository.findByDateProductBrand(date, productId, brandId));
    }
}
