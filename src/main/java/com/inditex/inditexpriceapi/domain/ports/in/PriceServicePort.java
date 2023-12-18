package com.inditex.inditexpriceapi.domain.ports.in;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;

import java.time.LocalDateTime;

public interface PriceServicePort {
    ApplicablePrice getApplicablePrice(Long brandId, Long productId, LocalDateTime appliedDate);
}
