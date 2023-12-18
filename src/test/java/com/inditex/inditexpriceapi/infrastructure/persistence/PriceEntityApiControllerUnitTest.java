package com.inditex.inditexpriceapi.infrastructure.persistence;

import com.inditex.inditexpriceapi.application.service.PriceService;
import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.infrastructure.api.api.PriceApiController;
import com.inditex.inditexpriceapi.infrastructure.api.delegate.PriceApiDelegateImpl;
import com.inditex.inditexpriceapi.infrastructure.api.mapper.PriceMapper;
import com.inditex.inditexpriceapi.infrastructure.api.model.PriceDTO;
import com.inditex.inditexpriceapi.infrastructure.config.GlobalExceptionHandler;
import com.inditex.inditexpriceapi.infrastructure.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PriceEntityApiControllerUnitTest {

    @Mock
    private PriceService priceService;
    @Mock
    private PriceMapper priceMapper;
    @InjectMocks
    private PriceApiDelegateImpl priceApiDelegateImpl;

    @Test
    void whenDatabaseErrorOccursThenReturnsServerError() {
        when(priceService.getApplicablePrice(anyLong(), anyLong(), any()))
                .thenThrow(new RuntimeException("Simulated Database Error"));

        assertThrows(RuntimeException.class, () -> priceApiDelegateImpl
                .getApplicablePrice(1L, 35455L, "2020-06-14 10:00:00"));

    }

    @Test
    void whenValidRequestThenReturnsPriceDTO() {
        ApplicablePrice mockPriceDTO = createMockPriceDTO();
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBrandId(1L);
        priceDTO.setProductId(35455L);
        priceDTO.setApplicablePrice(BigDecimal.valueOf(35.50));

        when(priceService.getApplicablePrice(anyLong(), anyLong(), any())).thenReturn(mockPriceDTO);
        when(priceMapper.toDto(any())).thenReturn(priceDTO);

        ResponseEntity<PriceDTO> actualResponse = priceApiDelegateImpl
                .getApplicablePrice(1L, 35455L, "2020-06-14 10:00:00");

        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getBody());
        assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(actualResponse.getBody().getBrandId(), 1L);
        assertEquals(actualResponse.getBody().getProductId(), 35455L);
        assertEquals(actualResponse.getBody().getApplicablePrice(),  BigDecimal.valueOf(35.50));
    }

    @Test
    void whenPriceNotFoundThenReturnsNotFound() {
        when(priceService.getApplicablePrice(anyLong(), anyLong(), any())).thenReturn(null);
        when(priceMapper.toDto(any())).thenReturn(null);

        assertThrows(PriceNotFoundException.class, () -> priceApiDelegateImpl
                .getApplicablePrice(1L, 35455L, "2020-06-14 10:00:00"));

    }

    private ApplicablePrice createMockPriceDTO() {
        return new ApplicablePrice() {
            @Override
            public Long getBrandId() {
                return 1L;
            }

            @Override
            public Long getProductId() {
                return 35455L;
            }

            @Override
            public Long getPriceRangeId() {
                return null;
            }

            @Override
            public LocalDateTime getStartDate() {
                return null;
            }

            @Override
            public LocalDateTime getEndDate() {
                return null;
            }

            @Override
            public BigDecimal getApplicablePrice() {
                return new BigDecimal("35.50");
            }
        };
    }

}
