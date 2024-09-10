package com.stock.domain.api;

import com.stock.domain.model.Product;

import java.util.List;

public interface IProductServicePort {
    void saveProduct(Product product, Long brandId, List<Long> categoryIds);
}
