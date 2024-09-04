package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.stock.domain.model.Brand;
import com.stock.domain.model.BrandFactory;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandAdapterTest {
    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void When_BrandIsSaved_Expect_SaveInRepository() {
        // Arrange
        Brand brand = BrandFactory.createdBrandDefault();
        BrandEntity brandEntity = BrandEntityFactory.brandEntityDefault();
        when(brandEntityMapper.toEntity(brand)).thenReturn(brandEntity);

        // Act
        brandAdapter.saveBrand(brand);

        // Assert
        verify(brandRepository).save(brandEntity);
    }

    @Test
    void When_ExistsByNameIsCalled_Expect_ReturnValueFromRepository() {
        // Arrange
        String name = DomainConstants.DEFAULT_NAME;
        when(brandRepository.existsByName(name)).thenReturn(true);

        // Act
        boolean exists = brandAdapter.existsByName(name);

        // Assert
        assertTrue(exists);
    }

}