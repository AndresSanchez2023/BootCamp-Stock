package com.stock.adapters.driving.http.mapper;

import com.stock.adapters.driving.http.dto.request.AddProductRequest;
import com.stock.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "categoryList", ignore = true)
    Product addRequestToProduct(AddProductRequest addProductRequest);
}
