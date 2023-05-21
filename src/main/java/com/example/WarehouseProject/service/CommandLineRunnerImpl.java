package com.example.WarehouseProject.service;
import com.example.WarehouseProject.entity.UserEntity;
import com.example.WarehouseProject.enums.UserRole;
import com.example.WarehouseProject.repository.UserRepository;
import com.example.WarehouseProject.util.MD5Util;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataSource dataSource;
    @Override
    public void run(String... args) throws Exception {
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
        String phone = "12345";
        Optional<UserEntity> userEntity = userRepository.findByPhoneNumber(phone);
        if (userEntity.isEmpty()) {
            UserEntity entity = new UserEntity();
            entity.setFirstName("Admin");
            entity.setLastName("Adminov");
            entity.setCode("Code1");
            entity.setPhoneNumber("12345");
            entity.setPassword(MD5Util.getMd5Hash("1"));
            entity.setActive(Boolean.TRUE);
            entity.setRole(UserRole.ROLE_ADMIN);
            userRepository.save(entity);
            System.out.println("Admin created");
        }
    }
}
