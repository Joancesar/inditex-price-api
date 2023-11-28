package com.inditex.inditexpriceapi.application.ports.out;

import com.inditex.inditexpriceapi.application.model.PriceDTO;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    PriceDTO findApplicablePrice(long productId, long brandId, LocalDateTime appliedDate);
}