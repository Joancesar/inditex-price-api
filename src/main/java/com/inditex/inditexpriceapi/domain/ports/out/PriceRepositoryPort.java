package com.inditex.inditexpriceapi.domain.ports.out;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    ApplicablePrice findApplicablePrice(long brandId, long productId, LocalDateTime appliedDate);
}