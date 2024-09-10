package com.stock.adapters.driving.http.adapter;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.AddBrandRequestFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginatedResponseFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginationFactory;
import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.api.usecase.PaginatedFactory;
import com.stock.domain.model.Brand;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.BrandFactory;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void When_PaginateBrands_Expect_PaginatedResponse() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationDefault();
        Paginated<Brand> paginated = PaginatedFactory.createdPaginatedBrandDefault();
        PaginatedResponse<BrandResponse> expectedResponse = PaginatedResponseFactory.createPaginatedBrandResponseDefault();

        when(brandServicePort.paginate(pagination)).thenReturn(paginated);
        when(brandRequestMapper.addPaginatedToResponse(paginated)).thenReturn(expectedResponse);

        // Act
        PaginatedResponse<BrandResponse> result = inBrandAdapter.getAllBrandsPaginated(pagination);

        // Assert
        assertEquals(expectedResponse, result);
    }
}