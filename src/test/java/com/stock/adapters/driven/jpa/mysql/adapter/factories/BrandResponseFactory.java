package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.domain.util.DomainConstants;

public class BrandResponseFactory {

    public static BrandResponse createDefaultBrandResponse (){
        return new BrandResponse(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
    }
}
