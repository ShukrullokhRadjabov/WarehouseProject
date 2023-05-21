package com.example.WarehouseProject.dto;

import com.example.WarehouseProject.entity.Input;
import com.example.WarehouseProject.entity.Output;
import com.example.WarehouseProject.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputProductResponseDTO {
    private Integer id;
    private ProductEntity product;
    private Double amount;
    private Double price;
    private Output output;
}
