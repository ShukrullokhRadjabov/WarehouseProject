package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.WarehouseEntity;
import com.example.WarehouseProject.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;
    @PostMapping("/private/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO addWarehouse(@RequestBody WarehouseEntity warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updateWarehouse(@PathVariable("id") Integer id, @RequestBody WarehouseEntity warehouse){
        return warehouseService.updateWarehouse(id, warehouse);
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteWarehouse(@PathVariable("id") Integer id){
        return warehouseService.deleteWarehouse(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ResponseDTO> listOfWarehouse(){
        return warehouseService.listOfWarehouse();
    }
}
