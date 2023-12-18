package com.inditex.inditexpriceapi.infrastructure.api.delegate;

import com.inditex.inditexpriceapi.domain.ports.in.PriceServicePort;
import com.inditex.inditexpriceapi.infrastructure.api.api.PriceApiDelegate;
import com.inditex.inditexpriceapi.infrastructure.api.mapper.PriceMapper;
import com.inditex.inditexpriceapi.infrastructure.api.model.PriceDTO;
import com.inditex.inditexpriceapi.infrastructure.exception.PriceNotFoundException;
import com.inditex.inditexpriceapi.infrastructure.util.DateConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceApiDelegateImpl implements PriceApiDelegate {

    private final PriceServicePort priceService;
    private final PriceMapper priceMapper;

    public PriceApiDelegateImpl(PriceServicePort priceService, PriceMapper priceMapper) {
        this.priceService = priceService;
        this.priceMapper = priceMapper;
    }

    @Override
    public ResponseEntity<PriceDTO> getApplicablePrice(Long brandId, Long productId, String date) {
        return ResponseEntity.ok(Optional.ofNullable(
                priceMapper.toDto(
                    priceService.getApplicablePrice(brandId, productId, DateConverter.parseDateTime(date)))
                )
                .orElseThrow(() -> new PriceNotFoundException(
                    String.format("Price not found for product id %d, brand id %d, with applicable date %s",
                            productId, brandId, date))
                ));
    }
}