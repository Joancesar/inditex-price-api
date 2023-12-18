package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.infrastructure.api.mapper.PriceMapper;
import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceEntityServiceUnitTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;
    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceService priceService;

    @Test
    void whenGetApplicablePriceIsCalledAndPriceExistsThenReturnPriceDTO() {
        long productId = 1L;
        long brandId = 1L;
        LocalDateTime appliedDate = LocalDateTime.now();
        ApplicablePrice expectedResponse = mock(ApplicablePrice.class);

        when(priceRepositoryPort.findApplicablePrice(brandId, productId, appliedDate))
                .thenReturn(expectedResponse);

        ApplicablePrice result = priceService.getApplicablePrice(brandId, productId, appliedDate);

        verify(priceRepositoryPort).findApplicablePrice(brandId, productId, appliedDate);
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }
}
