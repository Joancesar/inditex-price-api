package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.application.ports.PriceRepositoryPort;
import com.inditex.inditexpriceapi.domain.exception.PriceNotFoundException;
import com.inditex.inditexpriceapi.shared.model.PriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceUnitTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    @Test
    void whenGetApplicablePriceIsCalledAndPriceExistsThenReturnPriceDTO() {
        long productId = 1L;
        long brandId = 1L;
        LocalDateTime appliedDate = LocalDateTime.now();
        PriceDTO priceDTO = mock(PriceDTO.class);

        when(priceRepositoryPort.findApplicablePrice(productId, brandId, appliedDate))
                .thenReturn(Optional.of(priceDTO));

        Optional<PriceDTO> result = priceService.getApplicablePrice(productId, brandId, appliedDate);

        verify(priceRepositoryPort).findApplicablePrice(productId, brandId, appliedDate);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(priceDTO, result.get());
    }
}
