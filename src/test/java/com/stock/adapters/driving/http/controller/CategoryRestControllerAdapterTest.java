package com.stock.adapters.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginatedResponseFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginationFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginatedFactory;
import com.stock.adapters.driving.http.controller.CategoryRestControllerAdapter;
import com.stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.stock.adapters.driving.http.dto.response.CategoryResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.stock.domain.api.ICategoryServicePort;
import com.stock.domain.model.Category;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryFactory;
import com.stock.domain.model.Paginated;
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

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryRestControllerAdapter.class)
class CategoryRestControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICategoryServicePort categoryServicePort;

    @MockBean
    private ICategoryRequestMapper categoryRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_ShouldReturnCreatedStatus() throws Exception {

        Category category = CategoryFactory.createDefaultCategory();

        when(categoryRequestMapper.addRequestToCategory(any(AddCategoryRequest.class))).thenReturn(category);

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCategoryJson()))
                .andExpect(status().isCreated());

        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
        verify(categoryRequestMapper, times(1)).addRequestToCategory(any(AddCategoryRequest.class));
    }

    @Test
    void When_GetAllCategoriesPaginated_Expect_SuccessfulResponse() throws Exception {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationDefault();
        Category category = CategoryFactory.createDefaultCategory();
        Paginated<Category> paginated = PaginatedFactory.createPaginatedWithEntities(List.of(category));
        PaginatedResponse<CategoryResponse> paginatedResponse = PaginatedResponseFactory.createDefaultPaginatedResponse();

        when(categoryServicePort.paginate(any(Pagination.class))).thenReturn(paginated);
        when(categoryRequestMapper.addPaginatedToResponse(paginated)).thenReturn(paginatedResponse);

        // Act & Assert
        mockMvc.perform(get("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagination)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entities[0].name").value(DomainConstants.DEFAULT_NAME))
                .andExpect(jsonPath("$.totalItems").value(DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT))
                .andExpect(jsonPath("$.totalPages").value(DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT));
    }


    private String createCategoryJson() {
        return "{ \"name\": \"" + DomainConstants.DEFAULT_NAME + "\", \"description\": \"" + DomainConstants.DEFAULT_DESCRIPTION + "\" }";
    }
}
