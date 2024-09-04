package com.stock.adapters.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.adapters.driving.http.adapter.AddBrandRequestFactory;
import com.stock.adapters.driving.http.adapter.InBrandAdapter;
import com.stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.stock.domain.exception.BrandAlreadyExistsException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mockMvc.perform(post("/brand/")
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
        mockMvc.perform(post("/brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

}