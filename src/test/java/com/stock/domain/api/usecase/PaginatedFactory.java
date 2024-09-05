package com.stock.domain.api.usecase;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.BrandFactory;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Category;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryFactory;
import com.stock.domain.model.Paginated;
import com.stock.domain.util.DomainConstants;

import java.util.ArrayList;
import java.util.List;

public class PaginatedFactory {
    public static Paginated<Category> createdPaginatedWithListEmpty() {
        return new Paginated<>(listEmpty(), DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.MESSAGE_NO_CATEGORIES_REGISTERED);
    }

    public static Paginated<Category> createdPaginatedDefault() {
        return new Paginated<>(listCategory(), DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.PAGINATION_SUCCESSFUL);
    }

    public static List<Category> listEmpty() {
        return new ArrayList<>();
    }

    public static List<Category> listCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(CategoryFactory.createDefaultCategory());
        return categories;
    }

    public static Paginated<Category> createPaginatedOutOfRange() {
        return new Paginated<>(listCategory(), DomainConstants.FIELD_PAGE_OUT_RANGE, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE);
    }

    //Brand

    public static Paginated<Brand> createdPaginatedBrandWithListEmpty() {
        return new Paginated<>(listEmptyBrand(), DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.MESSAGE_NO_CATEGORIES_REGISTERED);
    }

    public static List<Brand> listEmptyBrand() {
        return new ArrayList<>();
    }

    public static Paginated<Brand> createPaginatedBrandOutOfRange() {
        return new Paginated<>(List.of(BrandFactory.createdBrandDefault()), DomainConstants.FIELD_PAGE_OUT_RANGE, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE);
    }

    public static Paginated<Brand> createdPaginatedBrandDefault() {
        return new Paginated<>(List.of(BrandFactory.createdBrandDefault()), DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.PAGINATION_SUCCESSFUL);
    }
}
