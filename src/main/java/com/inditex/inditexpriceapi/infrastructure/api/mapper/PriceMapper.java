package com.inditex.inditexpriceapi.infrastructure.api.mapper;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditex.inditexpriceapi.infrastructure.api.model.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDTO toDto(ApplicablePrice applicablePrice);
}
