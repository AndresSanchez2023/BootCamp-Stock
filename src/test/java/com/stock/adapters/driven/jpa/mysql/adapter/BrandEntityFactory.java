package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.stock.domain.util.DomainConstants;

public class BrandEntityFactory {
    public static BrandEntity brandEntityDefault() {
        return new BrandEntity(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
    }
}
