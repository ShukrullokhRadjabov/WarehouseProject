package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.ClientEntity;
import com.example.WarehouseProject.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    boolean existsByPhoneNumber(String phone);
}
