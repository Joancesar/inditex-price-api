package com.inditex.inditexpriceapi.infrastructure.api;

import com.inditex.inditexpriceapi.domain.ports.in.PriceServicePort;
import com.inditex.inditexpriceapi.infrastructure.exception.PriceNotFoundException;
import com.inditex.inditexpriceapi.infrastructure.util.DateConverter;
import com.inditexpriceapi.application.model.PriceDTO;
import com.inditexpriceapi.infrastructure.api.PriceApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PriceApiController implements PriceApi {

    private final PriceServicePort priceService;

    public PriceApiController(PriceServicePort priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<PriceDTO> getApplicablePrice(Long brandId, Long productId, String date) {
        return ResponseEntity.ok(Optional.ofNullable(priceService.getApplicablePrice(brandId, productId,
                        DateConverter.parseDateTime(date)))
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for product id %d, brand id %d, with applicable date %s",
                                productId, brandId, date))
                ));
    }
}