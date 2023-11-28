package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.application.ports.in.PriceServicePort;
import com.inditex.inditexpriceapi.application.ports.out.PriceRepositoryPort;
import com.inditex.inditexpriceapi.shared.model.PriceDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.inditex.inditexpriceapi.infrastructure.config.CacheConstants.PRICES_CACHE;

@Service
public class PriceService implements PriceServicePort {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Cacheable(PRICES_CACHE)
    public PriceDTO getApplicablePrice(Long productId, Long brandId, LocalDateTime appliedDate) {
        return priceRepositoryPort.findApplicablePrice(productId, brandId, appliedDate);
    }
}
