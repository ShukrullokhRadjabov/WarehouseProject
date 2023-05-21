package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.MeasurementEntity;
import com.example.WarehouseProject.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Integer> {
    boolean existsByName(String name);

    Optional<WarehouseEntity> findByIdAndActive(Integer id, Boolean aTrue);

    List<WarehouseEntity> findAllByActive(boolean b);
}
