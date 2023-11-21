package com.inditex.inditexpriceapi.infrastructure.persistence;

import com.inditex.inditexpriceapi.application.service.PriceService;
import com.inditex.inditexpriceapi.infrastructure.api.BrandApiController;
import com.inditex.inditexpriceapi.infrastructure.config.GlobalExceptionHandler;
import com.inditex.inditexpriceapi.shared.model.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BrandApiControllerUnitTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private BrandApiController brandApiController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(brandApiController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
            .alwaysDo(MockMvcResultHandlers.print())
            .build();
    }

    @Test
    void whenDatabaseErrorOccursThenReturnsServerError() throws Exception {
        when(priceService.getApplicablePrice(anyLong(), anyLong(), any()))
                .thenThrow(new RuntimeException("Simulated Database Error"));

        mockMvc.perform(get("/v1/brands/1/products/35455/prices")
                .param("date", "2020-06-14 10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void whenValidRequestThenReturnsPriceDTO() throws Exception {
        PriceDTO mockPriceDTO = createMockPriceDTO();
        when(priceService.getApplicablePrice(anyLong(), anyLong(), any())).thenReturn(mockPriceDTO);

        mockMvc.perform(get("/v1/brands/1/products/35455/prices")
                        .param("date", "2020-06-14 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.applicablePrice").value(35.50));
    }

    @Test
    void whenPriceNotFoundThenReturnsNotFound() throws Exception {
        when(priceService.getApplicablePrice(anyLong(), anyLong(), any())).thenReturn(null);

        mockMvc.perform(get("/v1/brands/1/products/35455/prices")
                        .param("date", "2020-06-14 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private PriceDTO createMockPriceDTO() {
        return new PriceDTO() {
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