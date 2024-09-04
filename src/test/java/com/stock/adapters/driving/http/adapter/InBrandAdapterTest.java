package com.stock.adapters.driving.http.adapter;

import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.model.Brand;
import com.stock.domain.model.BrandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InBrandAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @InjectMocks
    private InBrandAdapter inBrandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void When_AddBrandRequestIsValid_Expect_BrandToBeSaved() {
        // Arrange
        AddBrandRequest request = AddBrandRequestFactory.addBrandRequestDefault();
        Brand brand = BrandFactory.createdBrandDefault();
        when(brandRequestMapper.addRequestToBrand(request)).thenReturn(brand);

        // Act
        inBrandAdapter.addBrand(request);

        // Assert
        verify(brandServicePort).saveBrand(brand);
    }
}