package com.example.WarehouseProject.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private Integer parentCategoryId;
}
