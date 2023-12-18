package com.inditex.inditexpriceapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ApplicablePrice {
    Long getBrandId();
    Long getProductId();
    Long getPriceRangeId();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    BigDecimal getApplicablePrice();
}
