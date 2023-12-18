package com.inditex.inditexpriceapi.application.mapper;

import com.inditex.inditexpriceapi.domain.model.ApplicablePrice;
import com.inditexpriceapi.application.model.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDTO toDto(ApplicablePrice applicablePrice);
}
