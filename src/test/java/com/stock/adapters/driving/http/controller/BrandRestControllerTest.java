package com.stock.adapters.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.AddBrandRequestFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginatedResponseFactory;
import com.stock.adapters.driving.http.adapter.InBrandAdapter;
import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.configuration.Constants;
import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.model.Pagination;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InBrandAdapter inBrandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void When_AddBrand_Expect_CreatedStatus() throws Exception {
        // Arrange
        AddBrandRequest request = AddBrandRequestFactory.addBrandRequestDefault();

        // Act & Assert
        mockMvc.perform(post(Constants.BRAND_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void When_AddBrandThatExists_Expect_BadRequestStatus() throws Exception {
        // Arrange
        AddBrandRequest request = AddBrandRequestFactory.addBrandRequestDefault();
        doThrow(new BrandAlreadyExistsException(DomainConstants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE))
                .when(inBrandAdapter).addBrand(any(AddBrandRequest.class));

        // Act & Assert
        mockMvc.perform(post(Constants.BRAND_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void When_GetAllBrandsPaginated_Expect_Success() throws Exception {
        // Arrange
        PaginatedResponse<BrandResponse> paginatedResponse = PaginatedResponseFactory.createPaginatedBrandResponseDefault();
        when(inBrandAdapter.getAllBrandsPaginated(any(Pagination.class))).thenReturn(paginatedResponse);

        // Act & Assert
        mockMvc.perform(get(Constants.BRAND_ENDPOINT)
                        .param(Constants.SORT_DIRECTION_STRING_VALUE_CONTROLLER, Constants.PAGE_DEFAULT_VALUE_CONTROLLER)
                        .param(Constants.SIZE_STRING_VALUE_CONTROLLER, Constants.SIZE_DEFAULT_VALUE_CONTROLLER)
                        .param(Constants.SORT_FIELD_STRING_VALUE_CONTROLLER, Constants.SORT_FIELD_DEFAULT_VALUE_CONTROLLER)
                        .param(Constants.SORT_DIRECTION_STRING_VALUE_CONTROLLER, Constants.SORT_DIRECTION_DEFAULT_VALUE_CONTROLLER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems").value(paginatedResponse.getTotalItems()))
                .andExpect(jsonPath("$.entities").isArray());
    }

    @Test
    void When_PaginationParamsAreInvalid_Expect_BadRequest() throws Exception {
        // Arrange
        doThrow(new InvalidArgumentsInFieldException(DomainConstants.VALUE_INVALID_FOR_SORT_FIELD))
                .when(inBrandAdapter).getAllBrandsPaginated(any(Pagination.class));

        // Act & Assert
        mockMvc.perform(get(Constants.BRAND_ENDPOINT)
                        .param(Constants.SORT_DIRECTION_STRING_VALUE_CONTROLLER, Constants.PAGE_DEFAULT_VALUE_CONTROLLER)
                        .param(Constants.SIZE_STRING_VALUE_CONTROLLER, Constants.SIZE_DEFAULT_VALUE_CONTROLLER)
                        .param(Constants.SORT_FIELD_STRING_VALUE_CONTROLLER, DomainConstants.INVALID_SORT_FIELD)
                        .param(Constants.SORT_DIRECTION_STRING_VALUE_CONTROLLER, Constants.SORT_DIRECTION_DEFAULT_VALUE_CONTROLLER))
                .andExpect(status().isBadRequest());
    }
}