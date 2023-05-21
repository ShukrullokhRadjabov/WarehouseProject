package com.example.WarehouseProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputProductRequestDTO {
    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
    private Integer day;
}
