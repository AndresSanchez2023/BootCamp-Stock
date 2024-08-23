package com.stock.adapters.driving.http.mapper;

import com.stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.stock.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);
}
