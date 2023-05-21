package com.example.WarehouseProject.dto;

import com.example.WarehouseProject.entity.CurrencyEntity;
import com.example.WarehouseProject.entity.SupplierEntity;
import com.example.WarehouseProject.entity.WarehouseEntity;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class InputResponseDTO {
    @NotNull
    private WarehouseEntity warehouse;
    private SupplierEntity supplier;
    private CurrencyEntity currency;
    private String factureNumber;
    private String code;


}
