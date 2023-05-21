package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.OutputProductRequestDTO;
import com.example.WarehouseProject.dto.OutputProductResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/output-product")
public class OutputProductController {
    @Autowired
    private OutputProductService outputProductService;
    @PostMapping("/private/create")
    public ResultDTO addOutputProduct( @RequestBody OutputProductRequestDTO outputProductRequestDTO){
        ResultDTO resultDTO = outputProductService.addOutputProduct(outputProductRequestDTO);
        return resultDTO;
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updpateOututProduct(@PathVariable("id") Integer id, @RequestBody OutputProductRequestDTO outputProductRequestDTO){
        ResultDTO resultDTO = outputProductService.updateOutputProduct(outputProductRequestDTO, id);
        return resultDTO;
    }

    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return outputProductService.deleteOutputProduct(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<OutputProductResponseDTO> listOfCurrency(){
        return outputProductService.listOfOutputProduct();
    }


}
