package com.example.WarehouseProject.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
@Getter
@Setter
public class InputDTO {
    @NotNull
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
