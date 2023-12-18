package com.inditex.inditexpriceapi.infrastructure.persistence;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.domain.ports.out.PriceRepositoryPort;
import com.inditex.inditexpriceapi.infrastructure.persistence.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public ApplicablePrice findApplicablePrice(long brandId, long productId, LocalDateTime appliedDate) {
        return priceRepository.findApplicablePrice(brandId, productId, appliedDate);
    }
}
