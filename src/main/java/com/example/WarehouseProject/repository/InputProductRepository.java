package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.Input;
import com.example.WarehouseProject.entity.InputProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InputProductRepository extends CrudRepository<InputProduct, Integer> {
    Optional<InputProduct> findByIdAndActive(Integer id, boolean b);

    Iterable<InputProduct> findAllByActive(boolean b);
}
