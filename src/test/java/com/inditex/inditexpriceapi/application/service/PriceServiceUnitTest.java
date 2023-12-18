package com.inditex.inditexpriceapi.application.service;

import com.inditex.inditexpriceapi.application.mapper.PriceMapper;
import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.domain.ports.out.PriceRepositoryPort;
import com.inditexpriceapi.application.model.PriceDTO;
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
class PriceServiceUnitTest {
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
        ApplicablePrice applicablePrice = mock(ApplicablePrice.class);
        PriceDTO priceDto = mock(PriceDTO.class);

        when(priceRepositoryPort.findApplicablePrice(brandId, productId, appliedDate))
                .thenReturn(applicablePrice);
        when(priceMapper.toDto(applicablePrice)).thenReturn(priceDto);

        PriceDTO result = priceService.getApplicablePrice(brandId, productId, appliedDate);

        verify(priceRepositoryPort).findApplicablePrice(brandId, productId, appliedDate);
        assertNotNull(result);
        assertEquals(priceDto, result);
    }
}
