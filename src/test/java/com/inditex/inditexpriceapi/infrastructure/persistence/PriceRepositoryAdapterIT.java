package com.inditex.inditexpriceapi.infrastructure.persistence;

import com.inditex.inditexpriceapi.shared.model.PriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceRepositoryAdapterIT {

    @Autowired
    private PriceRepositoryAdapter priceRepositoryAdapter;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void whenFindApplicablePriceCalledWithExistingDataThenPriceIsReturned() {
        long productId = 35455L;
        long brandId = 1L;
        LocalDateTime appliedDate = LocalDateTime.parse("2020-06-14 10:00:00", formatter);

        Optional<PriceDTO> result = priceRepositoryAdapter.findApplicablePrice(productId, brandId, appliedDate);

        assertTrue(result.isPresent());
        assertEquals(brandId, result.get().getBrandId());
        assertEquals(productId, result.get().getProductId());
        assertEquals(new BigDecimal("35.50"), result.get().getApplicablePrice());
    }

    @Test
    void whenFindApplicablePriceCalledWithNonExistingDataThenNoPriceIsReturned() {
        long productId = 35455L;
        long brandId = 1L;
        LocalDateTime appliedDate = LocalDateTime.parse("2021-01-01 10:00:00", formatter);

        Optional<PriceDTO> result = priceRepositoryAdapter.findApplicablePrice(productId, brandId, appliedDate);

        assertFalse(result.isPresent());
    }
}

