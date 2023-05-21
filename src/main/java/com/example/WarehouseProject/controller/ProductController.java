package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ProductDTO;
import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/private/create")
    public ResultDTO addProduct(@RequestBody ProductDTO productDTO){
        ResultDTO resultDTO = productService.addProduct(productDTO);
        return resultDTO;
    }

    @PutMapping("/private/update/{id}")
    public ResultDTO updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO){
        ResultDTO resultDTO = productService.updateProduct(productDTO, id);
        return resultDTO;
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductDTO> listOfCurrency(){
        return productService.listOfProduct();
    }


}
