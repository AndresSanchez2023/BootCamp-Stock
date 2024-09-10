package com.stock.configuration;

import com.stock.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.stock.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.stock.adapters.driven.jpa.mysql.adapter.JpaPaginationAdapter;
import com.stock.adapters.driven.jpa.mysql.adapter.ProductAdapter;
import com.stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.stock.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.stock.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.stock.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.stock.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.stock.adapters.driving.http.adapter.InBrandAdapter;
import com.stock.adapters.driving.http.adapter.InProductAdapter;
import com.stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.stock.adapters.driving.http.mapper.IProductRequestMapper;
import com.stock.domain.api.IBrandServicePort;
import com.stock.domain.api.ICategoryServicePort;
import com.stock.domain.api.IProductServicePort;
import com.stock.domain.api.usecase.BrandUseCase;
import com.stock.domain.api.usecase.CategoryUseCase;
import com.stock.domain.api.usecase.ProductUseCase;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Category;
import com.stock.domain.spi.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final IProductRequestMapper productRequestMapper;

    @Bean
    public CategoryAdapter categoryAdapter() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return categoryAdapter();
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort(), categoryPaginationPort());
    }

    @Bean
    public IPaginationPort<Category> categoryPaginationPort() {
        return new JpaPaginationAdapter<>(categoryRepository, categoryEntityMapper::toModel);
    }

    @Bean
    public BrandAdapter brandAdapter() {
        return new BrandAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return brandAdapter();
    }

    @Bean
    public IGetBrandByIdPort getBrandByIdPort() {
        return brandAdapter();
    }

    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort(), brandPaginationPort());
    }

    @Bean
    public InBrandAdapter inBrandAdapter() {
        return new InBrandAdapter(brandRequestMapper, brandServicePort());
    }

    @Bean
    public IPaginationPort<Brand> brandPaginationPort() {
        return new JpaPaginationAdapter<>(brandRepository, brandEntityMapper::toModel);
    }

    @Bean
    public IGetAllCategoryByIdPort getAllCategoryByIdPort() {
        return categoryAdapter();
    }

    @Bean
    public IProductPersistencePort productPersistencePort() {
        return new ProductAdapter(productRepository, productEntityMapper);
    }

    @Bean
    public IProductServicePort productServicePort() {
        return new ProductUseCase(productPersistencePort(), getBrandByIdPort(), getAllCategoryByIdPort());
    }

    @Bean
    public InProductAdapter inProductAdapter() {
        return new InProductAdapter(productRequestMapper, productServicePort());

    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title(Constants.TITLE_OPENAPI)
                        .version(Constants.VERSION_OPENAPI)
                        .description(Constants.DESCRIPTION_OPENAPI)
                );
    }
}
