package com.example.WarehouseProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierResponseDTO {
    private Integer id;
    private String name;
    private boolean active;
    private String phoneNumber;
}
