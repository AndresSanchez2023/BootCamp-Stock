package com.stock.adapters.driving.http.adapter;

import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Pagination;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InBrandAdapter {
    private final IBrandRequestMapper brandRequestMapper;

    private final IBrandServicePort brandServicePort;

    public void addBrand(AddBrandRequest brandRequest) {
        brandServicePort.saveBrand(addRequestToBrand(brandRequest));
    }

    public PaginatedResponse<BrandResponse> getAllBrandsPaginated(Pagination pagination) {

        return brandRequestMapper.addPaginatedToResponse
                (brandServicePort.paginate(pagination));
    }

    public Brand addRequestToBrand(AddBrandRequest brandRequest) {
        return brandRequestMapper.addRequestToBrand(brandRequest);
    }
}
