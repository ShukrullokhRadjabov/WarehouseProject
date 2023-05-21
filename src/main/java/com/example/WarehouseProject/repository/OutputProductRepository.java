package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.InputProduct;
import com.example.WarehouseProject.entity.OutputProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OutputProductRepository extends CrudRepository<OutputProduct, Integer> {
    Optional<OutputProduct> findByIdAndActive(Integer id, boolean b);

    Iterable<OutputProduct> findAllByActive(boolean b);
}
