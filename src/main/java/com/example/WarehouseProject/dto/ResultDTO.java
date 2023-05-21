package com.example.WarehouseProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDTO {

    private String message;
    private boolean status;
    private Object object;

    public ResultDTO(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
