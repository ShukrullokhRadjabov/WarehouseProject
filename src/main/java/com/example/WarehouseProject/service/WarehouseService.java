package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.WarehouseEntity;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.exceptions.NameIsBlankException;
import com.example.WarehouseProject.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    public ResultDTO addWarehouse(WarehouseEntity warehouse){
        if(warehouseRepository.existsByName(warehouse.getName())){
            return new ResultDTO("This warehouse already exist", false);
        }
        else {
            warehouseRepository.save(warehouse);
            return new ResultDTO("New warehouse successfully added", true);
        }
    }

    public ResultDTO updateWarehouse(Integer id, WarehouseEntity warehouse) {
        Optional<WarehouseEntity> optional = warehouseRepository.findByIdAndActive(id, Boolean.TRUE);
        if(!optional.isPresent()){
            throw new ItemNotFoundException("Such warehouse not found");
        }
        if(warehouse.getName()==null || warehouse.getName().isBlank()){
            throw new NameIsBlankException("Warehouse name is blank");
        }
        WarehouseEntity warehouseEntity = optional.get();
        warehouseEntity.setName(warehouse.getName());
        warehouseRepository.save(warehouseEntity);
        return new ResultDTO("Successfully update", true);
    }

    public ResultDTO deleteWarehouse(Integer id) {
        Optional<WarehouseEntity> byId = warehouseRepository.findByIdAndActive(id, true);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }
        WarehouseEntity warehouseEntity = byId.get();
        warehouseEntity.setActive(false);
        warehouseRepository.save(warehouseEntity);
        return new ResultDTO("Successfully delete", true);
    }

    public List<ResponseDTO> listOfWarehouse() {
        List<WarehouseEntity> listOf = warehouseRepository.findAllByActive(true);
        if(listOf.isEmpty()){
            throw new ItemNotFoundException("Item not found");
        }
        List<ResponseDTO> dtoList = new LinkedList<>();
        for (WarehouseEntity warehouseEntity : listOf) {
            ResponseDTO dto = new ResponseDTO();
            dto.setId(warehouseEntity.getId());
            dto.setName(warehouseEntity.getName());
            dto.setActive(warehouseEntity.isActive());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
