package com.stock.adapters.driven.jpa.mysql.repository;

import com.stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
    boolean existsByName(String name);
}
