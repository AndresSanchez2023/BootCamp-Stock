package com.stock.domain.model;

import com.stock.domain.util.DomainConstants;

public class BrandFactory {
    public static Brand createdBrandDefault(){
        return new Brand(DomainConstants.DEFAULT_ID,DomainConstants.DEFAULT_NAME,DomainConstants.DEFAULT_DESCRIPTION);
    }

}
