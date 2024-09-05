package com.stock.domain.api.usecase;

import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import com.stock.domain.spi.IBrandPersistencePort;
import com.stock.domain.spi.IPaginationPort;
import com.stock.domain.util.DomainConstants;

import java.util.Collections;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    private final IPaginationPort<Brand> brandPaginationPort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort, IPaginationPort<Brand> brandPaginationPort) {
        this.brandPersistencePort = brandPersistencePort;
        this.brandPaginationPort = brandPaginationPort;
    }

    @Override
    public void saveBrand(Brand brand) {
        boolean existsBrand = brandPersistencePort.existsByName(brand.getName());
        if (existsBrand) {
            throw new BrandAlreadyExistsException(DomainConstants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Paginated<Brand> paginate(Pagination pagination) {
        validateSortField(pagination.getSort());
        validateSortDirection(pagination.getSortDirection());
        Integer page = pagination.getPage();
        Integer size = pagination.getSize();
        Long totalItems = getTotalItems();
        int totalPages = calculateTotalPages(totalItems, size);
        if (totalItems == 0) {
            return new Paginated<>(Collections.emptyList(), page, totalItems, totalPages, DomainConstants.MESSAGE_NO_CATEGORIES_REGISTERED);
        }
        if ((long) page * size >= totalItems) {
            return new Paginated<>(Collections.emptyList(), page, totalItems, totalPages, DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE + totalPages);
        }
        return brandPaginationPort.paginate(pagination);
    }
    
    @Override
    public Long getTotalItems() {
        return brandPersistencePort.getTotalItems();
    }

    private void validateSortDirection(String sortDirection) {
        if (!DomainConstants.VALUE_ASC.equalsIgnoreCase(sortDirection) && !DomainConstants.VALUE_DESC.equalsIgnoreCase(sortDirection)) {
            throw new InvalidArgumentsInFieldException(DomainConstants.VALUE_INVALID_FOR_SORT_DIRECTION);
        }
    }
    private int calculateTotalPages(Long totalItems, int size) {
        return (int) Math.ceil((double) totalItems / size);
    }

    private void validateSortField(String sort) {
        if (!sort.equalsIgnoreCase(DomainConstants.VALUE_NAME_SORT)) {
            throw new InvalidArgumentsInFieldException(DomainConstants.VALUE_INVALID_FOR_SORT_FIELD);
        }
    }
}
