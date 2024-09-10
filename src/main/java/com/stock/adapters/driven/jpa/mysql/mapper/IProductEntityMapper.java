package com.stock.adapters.driven.jpa.mysql.mapper;

import com.stock.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.stock.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductEntityMapper {

    Product toModel(ProductEntity productEntity);

    ProductEntity toEntity(Product product);
}
