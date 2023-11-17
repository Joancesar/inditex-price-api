package com.inditex.inditexpriceapi.application.ports;

import com.inditex.inditexpriceapi.domain.signatures.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<PriceDTO> findApplicablePrice(long productId, long brandId, LocalDateTime appliedDate);
}