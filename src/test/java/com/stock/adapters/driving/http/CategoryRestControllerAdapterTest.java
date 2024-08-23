package com.stock.adapters.driving.http;

import com.stock.adapters.driving.http.controller.CategoryRestControllerAdapter;
import com.stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.stock.domain.api.ICategoryServicePort;
import com.stock.domain.model.Category;
import com.stock.domain.model.CategoryFactory;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryRestControllerAdapter.class)
class CategoryRestControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryServicePort categoryServicePort;

    @MockBean
    private ICategoryRequestMapper categoryRequestMapper;

    @Test
    void addCategory_ShouldReturnCreatedStatus() throws Exception {

        //AddCategoryRequest addCategoryRequest = new AddCategoryRequest(DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
        Category category = CategoryFactory.createDefaultCategory();

        when(categoryRequestMapper.addRequestToCategory(any(AddCategoryRequest.class))).thenReturn(category);

        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCategoryJson()))
                .andExpect(status().isCreated());

        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
        verify(categoryRequestMapper, times(1)).addRequestToCategory(any(AddCategoryRequest.class));
    }

    private String createCategoryJson() {
        return "{ \"name\": \"" + DomainConstants.DEFAULT_NAME + "\", \"description\": \"" + DomainConstants.DEFAULT_DESCRIPTION + "\" }";
    }
}
