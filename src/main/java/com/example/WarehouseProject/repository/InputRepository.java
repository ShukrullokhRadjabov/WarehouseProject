package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.Input;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InputRepository extends CrudRepository<Input, Integer> {
    Optional<Input> findByIdAndActive(Integer inputId, boolean b);
}
