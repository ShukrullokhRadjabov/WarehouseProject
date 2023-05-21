package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.Output;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OutputRepository extends CrudRepository<Output, Integer> {

    Optional<Output> findByIdAndActive(Integer outputId, boolean b);
}
