package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Integer> {
    boolean existsByName(String name);

    Optional<MeasurementEntity> findByIdAndActive(Integer measurementId, boolean b);
}
