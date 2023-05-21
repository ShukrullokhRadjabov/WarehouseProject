package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.CurrencyEntity;
import com.example.WarehouseProject.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;
    @PostMapping("/private/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO addCurrency(@RequestBody CurrencyEntity currency){
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updateCurrency(@PathVariable("id") Integer id, @RequestBody CurrencyEntity currency){
        return currencyService.updateCurrency(id, currency);
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return currencyService.deleteCurrency(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ResponseDTO> listOfCurrency(){
        return currencyService.listOfCurrency();
    }
}
