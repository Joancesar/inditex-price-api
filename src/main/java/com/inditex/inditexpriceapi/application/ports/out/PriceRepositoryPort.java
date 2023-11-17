package com.inditex.inditexpriceapi.application.ports.out;

import com.inditex.inditexpriceapi.domain.signatures.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<PriceDTO> findApplicablePrice(LocalDateTime appliedDate,
                                           long productId,
                                           long brandId);
}