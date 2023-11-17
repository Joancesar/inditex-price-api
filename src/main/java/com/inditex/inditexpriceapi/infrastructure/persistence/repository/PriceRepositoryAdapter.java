package com.inditex.inditexpriceapi.infrastructure.persistence.repository;

import com.inditex.inditexpriceapi.application.ports.out.PriceRepositoryPort;
import com.inditex.inditexpriceapi.domain.model.Price;
import com.inditex.inditexpriceapi.domain.signatures.PriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryAdapter extends PriceRepositoryPort, JpaRepository<Price, Long> {
    @Override
    @Query(value = "SELECT br.id as brandId,  " +
            "prod.id as productId,  " +
            "pr.id as priceRangeId,  " +
            "pr.start_date as startDate,  " +
            "pr.end_date as endDate,  " +
            "pr.price as applicablePrice  " +
            "FROM PRICE_RANGES pr  " +
            "INNER JOIN PRODUCTS prod ON pr.product_id = prod.id  " +
            "INNER JOIN BRANDS br ON prod.brand_id = br.id  " +
            "WHERE pr.product_id = :productId  " +
            "AND prod.brand_id = :brandId  " +
            "AND :appliedDate BETWEEN pr.start_date AND pr.end_date  " +
            "ORDER BY pr.priority DESC " +
            "LIMIT 1 ", nativeQuery = true)
    Optional<PriceDTO> findApplicablePrice(LocalDateTime appliedDate, long productId, long brandId);
}