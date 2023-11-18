package com.inditex.inditexpriceapi.shared.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
public interface PriceDTO {
    Long getBrandId();
    Long getProductId();
    Long getPriceRangeId();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    BigDecimal getApplicablePrice();
}