package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.domain.util.DomainConstants;

public class AddBrandRequestFactory {

    public static AddBrandRequest addBrandRequestDefault(){
        return new AddBrandRequest(DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
    }
}
