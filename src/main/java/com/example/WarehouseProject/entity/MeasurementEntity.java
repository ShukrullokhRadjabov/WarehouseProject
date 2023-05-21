package com.example.WarehouseProject.entity;

import com.example.WarehouseProject.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "measurement")
public class MeasurementEntity extends AbstractEntity {

}

