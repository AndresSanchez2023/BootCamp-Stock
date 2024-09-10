package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.domain.model.Product;
import com.stock.domain.util.DomainConstants;

import java.math.BigDecimal;

public class ProductFactory {

    public static Product createdProductDefault() {
        return new Product(null, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION, DomainConstants.DEFAULT_QUANTITY, DomainConstants.DEFAULT_PRICE, null, null);
    }

    public static Product createdProductWithNameNull() {
        return new Product(DomainConstants.DEFAULT_ID, null, DomainConstants.DEFAULT_DESCRIPTION, DomainConstants.DEFAULT_QUANTITY, DomainConstants.DEFAULT_PRICE, null, null);
    }

    public static Product createdProductWithNameBlank() {
        return new Product(DomainConstants.DEFAULT_ID, DomainConstants.NAME_BLANK, DomainConstants.DEFAULT_DESCRIPTION, DomainConstants.DEFAULT_QUANTITY, DomainConstants.DEFAULT_PRICE, null, null);
    }

    public static Product createdProductWithDescriptionNull() {
        return new Product(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, null, DomainConstants.DEFAULT_QUANTITY, DomainConstants.DEFAULT_PRICE, null, null);
    }

    public static Product createdProductWithDescriptionBlank() {
        return new Product(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, DomainConstants.DESCRIPTION_BLANK, DomainConstants.DEFAULT_QUANTITY, DomainConstants.DEFAULT_PRICE, null, null);
    }

    public static Product createdProductWithQuantityNull() {
        return new Product(null, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION, null, DomainConstants.DEFAULT_PRICE, null, null);
    }

    public static Product createdProductWithQuantityZero() {
        return new Product(null, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION, DomainConstants.DEFAULT_0L, DomainConstants.DEFAULT_PRICE, null, null);
    }
    public static Product createdProductWithPriceNull() {
        return new Product(null, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION, DomainConstants.DEFAULT_QUANTITY, null, null, null);
    }
    public static Product createdProductWithPriceZero() {
        return new Product(null, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION, DomainConstants.DEFAULT_QUANTITY, BigDecimal.ZERO, null, null);
    }
}
