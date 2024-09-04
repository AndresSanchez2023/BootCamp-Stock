package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryEntityFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PageFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginationFactory;
import com.stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.stock.domain.model.Category;
import com.stock.domain.model.CategoryFactory;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaPaginationAdapterTest {

    @Mock
    private JpaRepository<CategoryEntity, Long> repository;

    @Mock
    private Function<CategoryEntity, Category> entityToModelMapper;

    @InjectMocks
    private JpaPaginationAdapter<Category, CategoryEntity> jpaPaginationAdapter;

    @Test
    void When_PaginateWithValidPagination_Expect_SuccessfulResult() {
        // Arrange
        CategoryEntity categoryEntity = CategoryEntityFactory.createDefaultCategoryEntity();
        List<CategoryEntity> entities = List.of(categoryEntity);
        Page<CategoryEntity> page = PageFactory.createPageWithEntities(entities);
        when(repository.findAll(any(Pageable.class))).thenReturn(page);
        when(entityToModelMapper.apply(categoryEntity)).thenReturn(CategoryFactory.createDefaultCategory());

        Pagination pagination = PaginationFactory.createPaginationDefault();

        // Act
        Paginated<Category> result = jpaPaginationAdapter.paginate(pagination);

        // Assert
        assertThat(result.getEntities()).hasSize(1);
        assertThat(result.getEntities().get(0).getName()).isEqualTo(DomainConstants.DEFAULT_NAME);
        assertThat(result.getTotalItems()).isEqualTo(1);
        assertThat(result.getTotalPages()).isEqualTo(1);
    }
}
