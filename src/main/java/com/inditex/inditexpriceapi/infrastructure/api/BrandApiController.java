package com.inditex.inditexpriceapi.infrastructure.api;

import com.inditex.inditexpriceapi.application.service.PriceService;
import com.inditex.inditexpriceapi.shared.model.PriceDTO;
import com.inditex.inditexpriceapi.infrastructure.config.ApiPathConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(ApiPathConstants.V1 + ApiPathConstants.BRANDS_ROUTE)
public class BrandApiController {

    private final PriceService priceService;

    public BrandApiController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/{brandId}/products/{productId}/prices")
    public ResponseEntity<PriceDTO> getPrice(@PathVariable long productId,
                                             @PathVariable long brandId,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                             LocalDateTime date) {
        return ResponseEntity.ok(priceService.getApplicablePrice(productId, brandId, date));
    }
}