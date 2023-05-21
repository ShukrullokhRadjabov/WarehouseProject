package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.ResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.CurrencyEntity;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.exceptions.NameIsBlankException;
import com.example.WarehouseProject.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;
    public ResultDTO addCurrency(CurrencyEntity currency){
        if(currencyRepository.existsByName(currency.getName())){
            return new ResultDTO("This currency already exist", false);
        }
        else {
            currencyRepository.save(currency);
            return new ResultDTO("New currency successfully added", true);
        }
    }

    public ResultDTO updateCurrency(Integer id, CurrencyEntity currency) {
        Optional<CurrencyEntity> optional = currencyRepository.findById(id);
        if(!optional.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }
        if(currency.getName().isEmpty()){
            throw new NameIsBlankException("Name is blank");
        }
        CurrencyEntity currencyEntity = optional.get();
        currencyEntity.setName(currency.getName());
        currencyRepository.save(currencyEntity);
        return new ResultDTO("Successfully update", true);
    }

    public ResultDTO deleteCurrency(Integer id) {
        Optional<CurrencyEntity> byId = currencyRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }
        CurrencyEntity currencyEntity = byId.get();
        currencyEntity.setActive(false);
        currencyRepository.save(currencyEntity);
        return new ResultDTO("Successfully delete", true);
    }

    public List<ResponseDTO> listOfCurrency() {
        List<CurrencyEntity> listOf = currencyRepository.findAll();
        if(listOf.isEmpty()){
            throw new ItemNotFoundException("Item not found");
        }
        List<ResponseDTO> dtoList = new LinkedList<>();
        for (CurrencyEntity currencyEntity : listOf) {
            ResponseDTO dto = new ResponseDTO();
            dto.setId(currencyEntity.getId());
            dto.setName(currencyEntity.getName());
            dto.setActive(currencyEntity.isActive());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
