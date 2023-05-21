package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.CurrencyEntity;
import com.example.WarehouseProject.entity.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    boolean existsByName(String name);
}
