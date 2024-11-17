package com.example.inix.infrastructure.adapter;

import com.example.inix.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IPriceCrudRepository extends CrudRepository<PriceEntity, Integer> {

    @Query(value = "select * from prices p " +
            "where :date between p.start_date and p.end_date " +
            "and p.product_id = :productId and p.brand_id = :brandId " +
            "and p.priority IN (select max(priority) from prices p " +
            "where :date between p.start_date and p.end_date " +
            "and p.product_id = :productId and p.brand_id = :brandId)", nativeQuery = true)
    PriceEntity findByDateProductBrand(
            @Param("date") LocalDateTime date,
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId);
}
