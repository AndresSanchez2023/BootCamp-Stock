package com.stock.adapters.driving.http.adapter;

import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.model.Brand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InBrandAdapter {
    private final IBrandRequestMapper brandRequestMapper;

    private final IBrandServicePort brandServicePort;

    public void addBrand (AddBrandRequest brandRequest){
        brandServicePort.saveBrand(addRequestToBrand(brandRequest));
    }

    public Brand addRequestToBrand (AddBrandRequest brandRequest){
        return brandRequestMapper.addRequestToBrand(brandRequest);
    }
}
