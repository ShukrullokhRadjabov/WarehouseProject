package com.example.WarehouseProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private boolean active;
    private Integer categoryId;
    private Integer attachId;
    private String code;
    private Integer measurementId;

}
