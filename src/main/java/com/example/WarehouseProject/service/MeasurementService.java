package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.entity.MeasurementEntity;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.exceptions.NameIsBlankException;
import com.example.WarehouseProject.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;
    public ResultDTO addMeasurement(MeasurementEntity measurement){
        if(measurementRepository.existsByName(measurement.getName())){
            return new ResultDTO("This measurement already exist", false);
        }
        else {
            measurementRepository.save(measurement);
            return new ResultDTO("New measurement successfully added", true);
        }
    }

    public ResultDTO updateMeasurement(Integer id, MeasurementEntity measurement) {
        Optional<MeasurementEntity> optional = measurementRepository.findById(id);
        if(!optional.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }
        if(measurement.getName().isEmpty()){
            throw new NameIsBlankException("Name is blank");
        }
        MeasurementEntity measurementEntity = optional.get();
        measurementEntity.setName(measurement.getName());
        measurementRepository.save(measurementEntity);
        return new ResultDTO("Successfully update", true);
    }

    public ResultDTO deleteMeasurement(Integer id) {
        Optional<MeasurementEntity> byId = measurementRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }
        MeasurementEntity measurementEntity = byId.get();
        measurementEntity.setActive(false);
        measurementRepository.save(measurementEntity);
        return new ResultDTO("Successfully delete", true);
    }

    public List<ResponseDTO> listOfMeasurement() {
        List<MeasurementEntity> listOf = measurementRepository.findAll();
        if(listOf.isEmpty()){
            throw new ItemNotFoundException("Item not found");
        }
        List<ResponseDTO> dtoList = new LinkedList<>();
        for (MeasurementEntity measurementEntity : listOf) {
            ResponseDTO dto = new ResponseDTO();
            dto.setId(measurementEntity.getId());
            dto.setName(measurementEntity.getName());
            dto.setActive(measurementEntity.isActive());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
