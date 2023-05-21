package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.CategoryEntity;
import com.example.WarehouseProject.entity.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByIdAndActive(Integer categoryId, boolean b);
}
