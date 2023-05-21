package com.example.WarehouseProject.controller;
import com.example.WarehouseProject.dto.InputProductRequestDTO;
import com.example.WarehouseProject.dto.InputProductResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/input-product")
public class InputProductController {
    @Autowired
    private InputProductService inputProductService;
    @PostMapping("/private/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO addInputProduct( @RequestBody InputProductRequestDTO inputProductRequestDTO){
        ResultDTO resultDTO = inputProductService.addInputProduct(inputProductRequestDTO);
        return resultDTO;
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updateInputProduct(@PathVariable("id") Integer id, @RequestBody InputProductRequestDTO inputProductRequestDTO){
        ResultDTO resultDTO = inputProductService.updateInputProduct(inputProductRequestDTO, id);
        return resultDTO;
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return inputProductService.deleteInputProduct(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<InputProductResponseDTO> listOfCurrency(){
        return inputProductService.listOfInputProduct();
    }


}
