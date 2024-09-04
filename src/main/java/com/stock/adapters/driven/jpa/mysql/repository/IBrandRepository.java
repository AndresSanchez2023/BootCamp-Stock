package com.stock.adapters.driven.jpa.mysql.repository;

import com.stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
}
