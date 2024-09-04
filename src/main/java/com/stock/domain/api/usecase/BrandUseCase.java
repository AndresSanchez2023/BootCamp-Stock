package com.stock.domain.api.usecase;

import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.model.Brand;
import com.stock.domain.spi.IBrandPersistencePort;
import com.stock.domain.util.DomainConstants;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        boolean existsBrand = brandPersistencePort.existsByName(brand.getName());
        if (existsBrand) {
            throw new BrandAlreadyExistsException(DomainConstants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        brandPersistencePort.saveBrand(brand);
    }
}
