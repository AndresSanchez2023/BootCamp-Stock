package com.stock.domain.spi;

import com.stock.domain.model.Brand;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Boolean existsByName(String name);
}
