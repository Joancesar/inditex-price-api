package com.inditex.inditexpriceapi.domain.ports.out;

import com.inditex.inditexpriceapi.application.model.PriceDTO;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    PriceDTO findApplicablePrice(long productId, long brandId, LocalDateTime appliedDate);
}