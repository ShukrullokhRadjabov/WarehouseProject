package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.SupplierEntity;
import com.example.WarehouseProject.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
    boolean existsByPhoneNumber(String phone);

    Optional<SupplierEntity> findByIdAndActive(Integer id, boolean b);

    List<SupplierEntity> findAllByActive(boolean b);
}
