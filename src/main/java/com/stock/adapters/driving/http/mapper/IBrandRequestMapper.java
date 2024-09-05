package com.stock.adapters.driving.http.mapper;

import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {
    @Mapping(target = "id", ignore = true)
    Brand addRequestToBrand (AddBrandRequest brandRequest);

    PaginatedResponse<BrandResponse> addPaginatedToResponse(Paginated<Brand> paginated);
}
