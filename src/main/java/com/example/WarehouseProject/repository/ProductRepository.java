package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Boolean existsByNameAndCategoryId(String name, Integer category_id);

    Iterable<ProductEntity> findAllByActive(boolean b);

    Optional<ProductEntity> findByIdAndActive(Integer id, boolean b);

}
