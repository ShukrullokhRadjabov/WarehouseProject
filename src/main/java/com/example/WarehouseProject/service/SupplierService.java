package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.dto.SupplierResponseDTO;
import com.example.WarehouseProject.entity.SupplierEntity;
import com.example.WarehouseProject.exceptions.AlreadyExistException;
import com.example.WarehouseProject.exceptions.ResponseBodyEmptyException;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    public ResultDTO addSupplier(SupplierEntity supplier){
        if(supplier.getPhoneNumber().isEmpty() || supplier.getPhoneNumber() == null){
            throw new ResponseBodyEmptyException("Phone number empty");
        }
        if(supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())){
            throw new AlreadyExistException("This supplier already exist");
        }
        else {
            supplierRepository.save(supplier);
            return new ResultDTO("New supplier successfully added", true);
        }
    }

    public ResultDTO updateSupplier(Integer id, SupplierEntity supplier) {
        Optional<SupplierEntity> optional = supplierRepository.findByIdAndActive(id, Boolean.TRUE);
        SupplierEntity entity = optional.get();
        if(!optional.isPresent()){
            throw new ItemNotFoundException("Supplier not found");
        }
        if(!supplier.getName().isEmpty()){
            entity.setName(supplier.getName());
        }
        if(!supplier.getPhoneNumber().isEmpty()){
            entity.setPhoneNumber(supplier.getPhoneNumber());
        }
        supplierRepository.save(entity);
        return new ResultDTO("Successfully update", true);
    }

    public ResultDTO deleteSupplier(Integer id) {
        Optional<SupplierEntity> byId = supplierRepository.findByIdAndActive(id, true);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }
        SupplierEntity supplierEntity = byId.get();
        supplierEntity.setActive(false);
        supplierRepository.save(supplierEntity);
        return new ResultDTO("Successfully delete", true);
    }

    public List<SupplierResponseDTO> listOfSupplier() {
        List<SupplierEntity> listOf = supplierRepository.findAllByActive(true);
        if(listOf.isEmpty()){
            throw new ItemNotFoundException("Item not found");
        }
        List<SupplierResponseDTO> dtoList = new LinkedList<>();
        for (SupplierEntity supplierEntity : listOf) {
            SupplierResponseDTO dto = new SupplierResponseDTO();
            dto.setId(supplierEntity.getId());
            dto.setName(supplierEntity.getName());
            dto.setActive(supplierEntity.isActive());
            dto.setPhoneNumber(supplierEntity.getPhoneNumber());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
