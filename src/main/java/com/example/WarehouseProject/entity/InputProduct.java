package com.example.WarehouseProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "input_product")
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ProductEntity product;
    @Column(nullable = false)
    private Double amount;
    private Double price;
    private LocalDate expireDate;
    @ManyToOne
    private Input input;
    private Boolean active = Boolean.TRUE;

}
