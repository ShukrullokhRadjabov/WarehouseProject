package com.example.WarehouseProject.service;
import com.example.WarehouseProject.dto.InputDTO;
import com.example.WarehouseProject.dto.InputResponseDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.CurrencyEntity;
import com.example.WarehouseProject.entity.Input;
import com.example.WarehouseProject.entity.SupplierEntity;
import com.example.WarehouseProject.entity.WarehouseEntity;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.CurrencyRepository;
import com.example.WarehouseProject.repository.InputRepository;
import com.example.WarehouseProject.repository.SupplierRepository;
import com.example.WarehouseProject.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class InputService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private InputRepository inputRepository;

    public ResultDTO addInput(InputDTO dto) {
        Optional<WarehouseEntity> warehouse = warehouseRepository.findById(dto.getWarehouseId());
        Optional<SupplierEntity> supplier = supplierRepository.findById(dto.getSupplierId());
        Optional<CurrencyEntity> currency = currencyRepository.findById(dto.getCurrencyId());
        if (!warehouse.isPresent()) {
            throw new ItemNotFoundException("Warehouse not found");
        }
        if (!supplier.isPresent()) {
            throw new ItemNotFoundException("Supplier not found");
        }
        if (!currency.isPresent()) {
            throw new ItemNotFoundException("Currency not found");
        }
        Input input = new Input();
        input.setWarehouse(warehouse.get());
        input.setSupplier(supplier.get());
        input.setCurrency(currency.get());
        input.setCode(dto.getCode());
        input.setDate(Timestamp.valueOf(LocalDateTime.now()));
        input.setFactureNumber(dto.getFactureNumber());
        inputRepository.save(input);
        return new ResultDTO("Input successfully saved", true);
    }

    public ResultDTO updateInput(InputDTO dto, Integer id) {
        Optional<Input> inputRepositoryById = inputRepository.findById(id);
        if (!inputRepositoryById.isPresent()) {
            throw new ItemNotFoundException("Input not found");
        }
        Optional<WarehouseEntity> warehouse = warehouseRepository.findById(dto.getWarehouseId());
        Optional<SupplierEntity> supplier = supplierRepository.findById(dto.getSupplierId());
        Optional<CurrencyEntity> currency = currencyRepository.findById(dto.getCurrencyId());
        if (!warehouse.isPresent()) {
            throw new ItemNotFoundException("Warehouse not found");
        }
        if (!supplier.isPresent()) {
            throw new ItemNotFoundException("Supplier not found");
        }
        if (!currency.isPresent()) {
            throw new ItemNotFoundException("Currency not found");
        }
        Input input = inputRepositoryById.get();
        input.setWarehouse(warehouse.get());
        input.setSupplier(supplier.get());
        input.setCurrency(currency.get());
        input.setCode(dto.getCode());
        input.setDate(Timestamp.valueOf(LocalDateTime.now()));
        input.setFactureNumber(dto.getFactureNumber());
        inputRepository.save(input);
        return new ResultDTO("Input successfully update", true);
    }

    public ResultDTO deleteInput(Integer id) {
        Optional<Input> inputRepositoryById = inputRepository.findById(id);
        if (!inputRepositoryById.isPresent()) {
            throw new ItemNotFoundException("Input not found");
        }
        Input input = inputRepositoryById.get();
        input.setActive(false);
        inputRepository.save(input);
        return new ResultDTO("Input delete", true);
    }

    public Set<InputResponseDTO> listOfInput() {
        Iterable<Input> inputs = inputRepository.findAll();
        if (inputs == null) {
            throw new ItemNotFoundException("List empty");
        }
        Set<InputResponseDTO> inputResponse = new HashSet<>();
        inputs.forEach(input -> {
            InputResponseDTO dto = new InputResponseDTO();
            dto.setWarehouse(input.getWarehouse());
            dto.setSupplier(input.getSupplier());
            dto.setCurrency(input.getCurrency());
            dto.setCode(input.getCode());
            dto.setFactureNumber(input.getFactureNumber());
            inputResponse.add(dto);
        });
        return inputResponse;
    }
}