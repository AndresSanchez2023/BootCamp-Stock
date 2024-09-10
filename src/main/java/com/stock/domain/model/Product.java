package com.stock.domain.model;

import com.stock.domain.exception.EmptyFieldException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.util.DomainConstants;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final Long quantity;
    private final BigDecimal price;
    private Brand brand;
    private List<Category> categoryList;

    public Product(Long id, String name, String description, Long quantity, BigDecimal price, Brand brand, List<Category> categoryList) {
        validatedFields(name, description, quantity, price);
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categoryList = categoryList;
    }

    private void validatedFields(String name, String description, Long quantity, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_NULL_MESSAGE);
        }
        if (description == null || description.isBlank()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        }
        if (quantity == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_QUANTITY_NOTNULL_MESSAGE);
        }
        if (price == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_PRICE_NOTNULL_MESSAGE);
        }
        if (quantity <= 0) {
            throw new InvalidArgumentsInFieldException(DomainConstants.FIELD_QUANTITY_POSITIVE_MESSAGE);
        }

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidArgumentsInFieldException(DomainConstants.FIELD_PRICE_POSITIVE_MESSAGE);
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
