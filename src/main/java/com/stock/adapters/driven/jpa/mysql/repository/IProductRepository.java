package com.stock.adapters.driven.jpa.mysql.repository;

import com.stock.adapters.driven.jpa.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
}
