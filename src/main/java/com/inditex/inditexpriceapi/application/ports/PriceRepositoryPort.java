package com.inditex.inditexpriceapi.application.ports;

import com.inditex.inditexpriceapi.shared.model.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    PriceDTO findApplicablePrice(long productId, long brandId, LocalDateTime appliedDate);
}