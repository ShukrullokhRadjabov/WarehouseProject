package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AttachRepository extends CrudRepository<AttachEntity, Integer> {
}
