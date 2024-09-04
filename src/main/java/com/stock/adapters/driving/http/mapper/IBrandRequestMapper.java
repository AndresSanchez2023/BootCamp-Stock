package com.stock.adapters.driving.http.mapper;

import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    Brand addRequestToBrand (AddBrandRequest brandRequest);
}
