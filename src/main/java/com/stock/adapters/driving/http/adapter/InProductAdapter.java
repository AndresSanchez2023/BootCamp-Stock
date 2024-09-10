package com.stock.adapters.driving.http.adapter;

import com.stock.adapters.driving.http.dto.request.AddProductRequest;
import com.stock.adapters.driving.http.mapper.IProductRequestMapper;
import com.stock.domain.api.IProductServicePort;
import com.stock.domain.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InProductAdapter {

    private final IProductRequestMapper productRequestMapper;
    private final IProductServicePort productServicePort;

    public void addProduct(AddProductRequest productRequest) {
        productServicePort.saveProduct(addProductRequest(productRequest), productRequest.getBrandId(), productRequest.getCategoryListId());
    }

    public Product addProductRequest(AddProductRequest productRequest) {
        return productRequestMapper.addRequestToProduct(productRequest);
    }
}
