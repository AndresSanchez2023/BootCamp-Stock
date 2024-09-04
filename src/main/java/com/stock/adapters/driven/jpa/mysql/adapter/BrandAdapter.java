package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.stock.domain.model.Brand;
import com.stock.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public Boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }
}
