package com.stock.domain.spi;

import com.stock.domain.model.Product;

public interface IProductPersistencePort {

    void saveProduct(Product product);

    boolean existsByName(String name);
}
