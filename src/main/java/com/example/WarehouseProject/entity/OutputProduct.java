package com.example.WarehouseProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "output_product")
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ProductEntity product;
    @Column(nullable = false)
    private Double amount;
    private Double price;
    @ManyToOne
    private Output output;
    private boolean active = Boolean.TRUE;
}
