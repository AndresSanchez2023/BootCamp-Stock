package com.stock.domain.api.usecase;

import com.stock.domain.api.ICategoryServicePort;
import com.stock.domain.exception.CategoryAlreadyExistsException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.model.Category;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import com.stock.domain.spi.ICategoryPersistencePort;
import com.stock.domain.spi.IPaginationPort;
import com.stock.domain.util.DomainConstants;

import java.util.Collections;


public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;
    private final IPaginationPort<Category> categoryPaginationPort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, IPaginationPort<Category> categoryPaginationPort) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryPaginationPort = categoryPaginationPort;
    }

    @Override
    public void saveCategory(Category category) {
        boolean categoryExists = categoryPersistencePort.existsByName(category.getName());
        if (categoryExists) {
            throw new CategoryAlreadyExistsException(DomainConstants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Paginated<Category> paginate(Pagination pagination) {
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
        return categoryPaginationPort.paginate(pagination);
    }

    @Override
    public Long getTotalItems() {
        return categoryPersistencePort.getTotalItems();
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
