package com.example.WarehouseProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp date;
    @ManyToOne
    private WarehouseEntity warehouse;
    @ManyToOne
    private SupplierEntity supplier;
    @ManyToOne
    private CurrencyEntity currency;
    private String factureNumber;
    @Column(unique = true, nullable = false)
    private String code;
    private boolean active=true;
}
