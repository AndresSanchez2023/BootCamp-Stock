package com.stock.domain.api.usecase;

import com.stock.domain.api.IProductServicePort;
import com.stock.domain.exception.*;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Category;
import com.stock.domain.model.Product;
import com.stock.domain.spi.IGetAllCategoryByIdPort;
import com.stock.domain.spi.IGetBrandByIdPort;
import com.stock.domain.spi.IProductPersistencePort;
import com.stock.domain.util.DomainConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductUseCase implements IProductServicePort {

    private final IProductPersistencePort productPersistencePort;
    private final IGetBrandByIdPort getBrandByIdPort;

    private final IGetAllCategoryByIdPort getAllCategoryById;

    public ProductUseCase(IProductPersistencePort productPersistencePort, IGetBrandByIdPort getBrandByIdPort, IGetAllCategoryByIdPort getAllCategoryById) {
        this.productPersistencePort = productPersistencePort;
        this.getBrandByIdPort = getBrandByIdPort;
        this.getAllCategoryById = getAllCategoryById;
    }

    @Override
    public void saveProduct(Product product, Long brandId, List<Long> categoryIds) {
        existsByName(product.getName());
        setBrandById(product, brandId);
        validatedListCategories(categoryIds);
        setCategoriesByIds(product, categoryIds);
        productPersistencePort.saveProduct(product);
    }

    private void existsByName(String name) {
        boolean existsProduct = productPersistencePort.existsByName(name);
        if (existsProduct) {
            throw new ProductAlreadyExistsException(DomainConstants.PRODUCT_ALREADY_EXISTS_EXCEPTION);
        }
    }

    private void setBrandById(Product product, Long brandId) {
        Brand brand = getBrandByIdPort.findById(brandId).orElseThrow(() ->
                new BrandNotFoundException(DomainConstants.BRAND_NOT_FOUND_EXCEPTION_MESSAGE));
        product.setBrand(brand);
    }

    private void setCategoriesByIds(Product product, List<Long> categoryIds) {

        List<Category> categories = getAllCategoryById.getAllCategoryById(categoryIds);

        if (categories.size() != categoryIds.size()) {
            throw new CategoryNotFoundException(DomainConstants.CATEGORIES_NOT_FOUND);
        }

        product.setCategoryList(categories);
    }

    private void validatedListCategories(List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.LIST_CATEGORY_IDS_IS_EMPTY);
        }
        if (categoryIds.size() > 3) {
            throw new ValidateSizeFieldException(DomainConstants.VALIDATE_LIST_ID_OF_CATEGORIES);
        }
        Set<Long> uniqueIds = new HashSet<>(categoryIds);
        if (uniqueIds.size() != categoryIds.size()) {
            throw new InvalidCategoryListException(DomainConstants.CATEGORY_LIST_CONTAINS_DUPLICATE_IDS);
        }
    }

}
