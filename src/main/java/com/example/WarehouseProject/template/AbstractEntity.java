package com.example.WarehouseProject.template;

import com.example.WarehouseProject.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean active = true;
}
