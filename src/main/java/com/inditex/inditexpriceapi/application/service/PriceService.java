package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.application.ports.PriceRepositoryPort;
import com.inditex.inditexpriceapi.shared.model.PriceDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Optional<PriceDTO> getApplicablePrice(Long productId, Long brandId, LocalDateTime appliedDate) {
        return priceRepositoryPort.findApplicablePrice(productId, brandId, appliedDate);
    }
}
