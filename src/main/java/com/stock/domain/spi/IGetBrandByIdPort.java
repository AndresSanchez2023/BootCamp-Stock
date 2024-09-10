package com.stock.domain.spi;

import com.stock.domain.model.Brand;

import java.util.Optional;

public interface IGetBrandByIdPort {

    Optional<Brand> findById(Long id);
}
