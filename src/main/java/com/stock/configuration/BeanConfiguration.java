package com.stock.configuration;

import com.stock.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.stock.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.stock.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.stock.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.stock.adapters.driving.http.adapter.InBrandAdapter;
import com.stock.adapters.driving.http.controller.BrandRestController;
import com.stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.api.ICategoryServicePort;
import com.stock.domain.api.usecase.BrandUseCase;
import com.stock.domain.api.usecase.CategoryUseCase;
import com.stock.domain.spi.IBrandPersistencePort;
import com.stock.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    private final IBrandRequestMapper brandRequestMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
    @Bean
    public InBrandAdapter inBrandAdapter(){
        return new InBrandAdapter(brandRequestMapper, brandServicePort());
    }
}
