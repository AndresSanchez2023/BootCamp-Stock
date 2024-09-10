package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.stock.domain.model.Product;
import com.stock.domain.spi.IProductPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {

    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
