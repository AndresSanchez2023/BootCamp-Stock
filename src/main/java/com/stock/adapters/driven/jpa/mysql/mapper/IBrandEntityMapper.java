package com.stock.adapters.driven.jpa.mysql.mapper;

import com.stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.stock.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel (BrandEntity brandEntity);
    BrandEntity toEntity (Brand brand);
}
