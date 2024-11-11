package com.example.inix.infrastructure.adapter;

import com.example.inix.infrastructure.entity.BrandEntity;
import org.springframework.data.repository.CrudRepository;

public interface IBrandCrudRepository extends CrudRepository<BrandEntity, Integer> {
}
