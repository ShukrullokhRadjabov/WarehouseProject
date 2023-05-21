package com.example.WarehouseProject.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputRequestDTO {
    private Integer warehouseId;
    private Integer clientId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
