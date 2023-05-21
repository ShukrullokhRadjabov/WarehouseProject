package com.example.WarehouseProject.repository;

import com.example.WarehouseProject.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByPhoneNumberAndPassword(String phone, String password);
}
