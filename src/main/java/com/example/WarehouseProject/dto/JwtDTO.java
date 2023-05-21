package com.example.WarehouseProject.dto;

import com.example.WarehouseProject.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
//
    private String phone;
    private UserRole role;
}
