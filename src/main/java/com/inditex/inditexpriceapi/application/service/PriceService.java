package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.application.ports.PriceRepositoryPort;
import com.inditex.inditexpriceapi.domain.exception.PriceNotFoundException;
import com.inditex.inditexpriceapi.domain.signatures.PriceDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public PriceDTO getApplicablePrice(Long productId, Long brandId, LocalDateTime appliedDate) {
        return priceRepositoryPort.findApplicablePrice(productId, brandId, appliedDate)
                .orElseThrow(() -> new PriceNotFoundException(
                    String.format("Price not found for product id %d, brand id %d, with applicable date %s",
                            productId, brandId, appliedDate.toString()))
                );
    }
}
