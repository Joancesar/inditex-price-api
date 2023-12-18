package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.application.mapper.PriceMapper;
import com.inditex.inditexpriceapi.domain.ports.in.PriceServicePort;
import com.inditex.inditexpriceapi.domain.ports.out.PriceRepositoryPort;
import com.inditexpriceapi.application.model.PriceDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.inditex.inditexpriceapi.infrastructure.config.CacheConstants.PRICES_CACHE;

@Service
public class PriceService implements PriceServicePort {

    private final PriceRepositoryPort priceRepositoryPort;
    private final PriceMapper priceMapper;

    public PriceService(PriceRepositoryPort priceRepositoryPort, PriceMapper priceMapper) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.priceMapper = priceMapper;
    }

    @Cacheable(PRICES_CACHE)
    public PriceDTO getApplicablePrice(Long brandId, Long productId, LocalDateTime appliedDate) {
        return priceMapper.toDto(priceRepositoryPort.findApplicablePrice(brandId, productId, appliedDate));
    }
}
