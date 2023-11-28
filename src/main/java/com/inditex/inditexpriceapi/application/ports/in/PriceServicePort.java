package com.inditex.inditexpriceapi.application.ports.in;

import com.inditex.inditexpriceapi.shared.model.PriceDTO;

import java.time.LocalDateTime;

public interface PriceServicePort {
    PriceDTO getApplicablePrice(Long productId, Long brandId, LocalDateTime appliedDate);
}
