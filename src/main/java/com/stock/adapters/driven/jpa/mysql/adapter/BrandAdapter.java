package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.stock.domain.model.Brand;
import com.stock.domain.spi.IBrandPersistencePort;
import com.stock.domain.spi.IGetBrandByIdPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort, IGetBrandByIdPort {
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

    @Override
    public Long getTotalItems() {
        return brandRepository.count();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        Optional<BrandEntity> optionalBrandEntity = brandRepository.findById(id);

        return optionalBrandEntity.map(brandEntityMapper::toModel);
    }
}
