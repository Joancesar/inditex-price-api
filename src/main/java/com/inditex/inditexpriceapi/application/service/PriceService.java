package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.domain.ports.in.PriceServicePort;
import com.inditex.inditexpriceapi.domain.ports.out.PriceRepositoryPort;
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
    public ApplicablePrice getApplicablePrice(Long brandId, Long productId, LocalDateTime appliedDate) {
        return priceRepositoryPort.findApplicablePrice(brandId, productId, appliedDate);
    }
}
