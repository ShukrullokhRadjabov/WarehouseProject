package com.example.WarehouseProject.entity;

import com.example.WarehouseProject.template.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class WarehouseEntity extends AbstractEntity {
}
