package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.dto.SupplierResponseDTO;
import com.example.WarehouseProject.entity.SupplierEntity;
import com.example.WarehouseProject.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @PostMapping("/private/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO addSupplier(@RequestBody SupplierEntity supplier){
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updateSupplier(@PathVariable("id") Integer id, @RequestBody SupplierEntity supplier){
        return supplierService.updateSupplier(id, supplier);
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteSupplier(@PathVariable("id") Integer id){
        return supplierService.deleteSupplier(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SupplierResponseDTO> listOfSupplier(){
        return supplierService.listOfSupplier();
    }
}
