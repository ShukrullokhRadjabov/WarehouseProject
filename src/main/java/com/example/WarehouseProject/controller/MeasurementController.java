package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.entity.MeasurementEntity;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/measurement")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;
    @PostMapping("/private/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO addMeasurement(@RequestBody MeasurementEntity measurement){
        return measurementService.addMeasurement(measurement);
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updateMeasurement(@PathVariable("id") Integer id, @RequestBody MeasurementEntity measurement){
        return measurementService.updateMeasurement(id, measurement);
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteMeasurement(@PathVariable("id") Integer id){
        return measurementService.deleteMeasurement(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ResponseDTO> listOfMeasurement(){
        return measurementService.listOfMeasurement();
    }
}
