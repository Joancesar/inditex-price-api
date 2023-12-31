package com.inditex.inditexpriceapi.infrastructure.persistence;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.infrastructure.persistence.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceEntityRepositoryAdapterIT {

    @Autowired
    private PriceRepository priceRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void whenFindApplicablePriceCalledWithExistingDataThenPriceIsReturned() {
        long productId = 35455L;
        long brandId = 1L;
        LocalDateTime appliedDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);

        ApplicablePrice result = priceRepository.findApplicablePrice(brandId, productId, appliedDate);

        assertEquals(brandId, result.getBrandId());
        assertEquals(productId, result.getProductId());
        assertEquals(new BigDecimal("35.50"), result.getApplicablePrice());
    }

    @Test
    void whenFindApplicablePriceCalledWithNonExistingDataThenNoPriceIsReturned() {
        long productId = 35455L;
        long brandId = 1L;
        LocalDateTime appliedDate = LocalDateTime.parse("2021-01-01 10:00:00", formatter);

        ApplicablePrice result = priceRepository.findApplicablePrice(brandId, productId, appliedDate);

        assertNull(result);
    }
}

