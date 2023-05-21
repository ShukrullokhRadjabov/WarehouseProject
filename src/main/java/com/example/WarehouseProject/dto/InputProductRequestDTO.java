package com.example.WarehouseProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputProductRequestDTO {
    private Integer productId;
    private Double amount;
    private Double price;
    private Integer inputId;
    private Integer day;
}
