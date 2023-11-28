package com.inditex.inditexpriceapi.infrastructure.api;

import com.inditex.inditexpriceapi.application.ports.in.PriceServicePort;
import com.inditex.inditexpriceapi.infrastructure.exception.PriceNotFoundException;
import com.inditex.inditexpriceapi.infrastructure.config.ApiPathConstants;
import com.inditex.inditexpriceapi.application.model.PriceDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(ApiPathConstants.V1 + ApiPathConstants.BRANDS_ROUTE)
public class BrandApiController {

    private final PriceServicePort priceService;

    public BrandApiController(PriceServicePort priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/{brandId}/products/{productId}/prices")
    public ResponseEntity<PriceDTO> getPrice(@PathVariable long productId,
                                             @PathVariable long brandId,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime date) {
        return ResponseEntity.ok(Optional.ofNullable(priceService.getApplicablePrice(productId, brandId, date))
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for product id %d, brand id %d, with applicable date %s",
                                productId, brandId, date.toString()))
                ));
    }
}