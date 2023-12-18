package com.inditex.inditexpriceapi.domain.ports.in;

import com.inditexpriceapi.application.model.PriceDTO;

import java.time.LocalDateTime;

public interface PriceServicePort {
    PriceDTO getApplicablePrice(Long brandId, Long productId, LocalDateTime appliedDate);
}
