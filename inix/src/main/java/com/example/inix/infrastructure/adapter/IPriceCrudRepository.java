package com.example.inix.infrastructure.adapter;

import com.example.inix.infrastructure.entity.PriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface IPriceCrudRepository extends CrudRepository<PriceEntity, Integer> {
}
