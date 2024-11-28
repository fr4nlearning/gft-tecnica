package com.example.inix.infrastructure.database.adapter;

import com.example.inix.infrastructure.database.entity.BrandEntity;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<BrandEntity, Integer> {
}
