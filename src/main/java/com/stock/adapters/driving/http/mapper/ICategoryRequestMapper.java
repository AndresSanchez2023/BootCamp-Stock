package com.stock.adapters.driving.http.mapper;

import com.stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.stock.adapters.driving.http.dto.response.CategoryResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.domain.model.Category;
import com.stock.domain.model.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    @Mapping(target = "id", ignore = true)
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);

    PaginatedResponse<CategoryResponse> addPaginatedToResponse(Paginated<Category> paginated);

    List<CategoryResponse> toCategoryResponse(List<Category> categoryList);

}
