package com.example.WarehouseProject.entity;

import com.example.WarehouseProject.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity extends AbstractEntity {
    @ManyToOne(optional = false)
    private CategoryEntity category;
    @OneToOne
    private AttachEntity photo;
    private String code;
    @ManyToOne(optional = false)
    private MeasurementEntity measurement;

}
