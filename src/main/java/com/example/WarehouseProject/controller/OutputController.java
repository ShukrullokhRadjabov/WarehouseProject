package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.OutputRequestDTO;
import com.example.WarehouseProject.dto.OutputResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/output")
public class OutputController {
    @Autowired
    private OutputService outputService;
    @PostMapping("/private/create")
    public ResultDTO addOutput( @RequestBody @Validated OutputRequestDTO outputRequestDTO){
        ResultDTO resultDTO = outputService.addOutput(outputRequestDTO);
        return resultDTO;
    }

    @PutMapping("/private/update/{id}")
    public ResultDTO updateOutput(@PathVariable("id") Integer id, @RequestBody OutputRequestDTO outputRequestDTO){
        ResultDTO resultDTO = outputService.updateOutput(outputRequestDTO, id);
        return resultDTO;
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return outputService.deleteOutput(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<OutputResponseDTO> listOfCurrency(){
        return outputService.listOfOutput();
    }


}
