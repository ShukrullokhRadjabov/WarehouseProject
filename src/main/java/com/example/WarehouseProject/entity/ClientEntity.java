package com.example.WarehouseProject.entity;

import com.example.WarehouseProject.template.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientEntity extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
