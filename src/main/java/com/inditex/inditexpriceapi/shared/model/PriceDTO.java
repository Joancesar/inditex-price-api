package com.inditex.inditexpriceapi.shared.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDTO(Long brandId, Long productId, Long priceRangeId,
                       LocalDateTime startDate, LocalDateTime endDate,
                       BigDecimal applicablePrice) {}