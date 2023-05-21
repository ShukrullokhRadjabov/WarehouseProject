package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.InputDTO;
import com.example.WarehouseProject.dto.InputResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/input")
public class InputController {
    @Autowired
    private InputService inputService;
    @PostMapping("/private/create")
    public ResultDTO addInput( @RequestBody @Validated InputDTO inputDTO){
        ResultDTO resultDTO = inputService.addInput(inputDTO);
        return resultDTO;
    }

    @PutMapping("/private/update/{id}")
    public ResultDTO updateInput(@PathVariable("id") Integer id, @RequestBody InputDTO inputDTO){
        ResultDTO resultDTO = inputService.updateInput(inputDTO, id);
        return resultDTO;
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return inputService.deleteInput(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<InputResponseDTO> listOfCurrency(){
        return inputService.listOfInput();
    }


}
