package com.stock.domain.api.usecase;


import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.model.Brand;
import com.stock.domain.model.BrandFactory;
import com.stock.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void When_BrandDoesNotExist_Expect_SaveBrand() {
        // Arrange
        Brand brand = BrandFactory.createdBrandDefault();
        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(false);

        // Act
        brandUseCase.saveBrand(brand);

        // Assert
        verify(brandPersistencePort).saveBrand(brand);
    }

    @Test
    void When_BrandExists_Expect_ThrowBrandAlreadyExistsException() {
        // Arrange
        Brand brand = BrandFactory.createdBrandDefault();
        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.saveBrand(brand));
    }
}

