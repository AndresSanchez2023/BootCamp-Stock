package com.stock.adapters.driven.jpa.mysql.mapper;

import com.stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.stock.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    Category toModel (CategoryEntity categoryEntity);
    CategoryEntity toEntity (Category category);
}
