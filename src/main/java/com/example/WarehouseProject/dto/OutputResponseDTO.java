package com.example.WarehouseProject.dto;
import com.example.WarehouseProject.entity.ClientEntity;
import com.example.WarehouseProject.entity.CurrencyEntity;
import com.example.WarehouseProject.entity.WarehouseEntity;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
@Getter
@Setter
public class OutputResponseDTO {
    private Integer id;
    private Timestamp date;
    private WarehouseEntity warehouse;
    private ClientEntity client;
    private CurrencyEntity currency;
    private String factureNumber;
    private String code;
}
