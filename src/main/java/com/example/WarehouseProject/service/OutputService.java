package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.*;
import com.example.WarehouseProject.entity.*;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OutputService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private OutputRepository outputRepository;

    public ResultDTO addOutput(OutputRequestDTO dto) {
        Optional<WarehouseEntity> warehouse = warehouseRepository.findById(dto.getWarehouseId());
        Optional<ClientEntity> client = clientRepository.findById(dto.getClientId());
        Optional<CurrencyEntity> currency = currencyRepository.findById(dto.getCurrencyId());
        if (!warehouse.isPresent()) {
            throw new ItemNotFoundException("Warehouse not found");
        }
        if (!client.isPresent()) {
            throw new ItemNotFoundException("Supplier not found");
        }
        if (!currency.isPresent()) {
            throw new ItemNotFoundException("Currency not found");
        }
        Output output = new Output();
        output.setWarehouse(warehouse.get());
        output.setClient(client.get());
        output.setCurrency(currency.get());
        output.setCode(dto.getCode());
        output.setDate(Timestamp.valueOf(LocalDateTime.now()));
        output.setFactureNumber(dto.getFactureNumber());
        outputRepository.save(output);
        return new ResultDTO("Output successfully saved", true);

    }

    public ResultDTO updateOutput(OutputRequestDTO dto, Integer id) {
        Optional<Output> outputRepositoryById = outputRepository.findById(id);
        if (!outputRepositoryById.isPresent()) {
            throw new ItemNotFoundException("Output not found");
        }
        Optional<WarehouseEntity> warehouse = warehouseRepository.findById(dto.getWarehouseId());
        Optional<ClientEntity> client = clientRepository.findById(dto.getClientId());
        Optional<CurrencyEntity> currency = currencyRepository.findById(dto.getCurrencyId());
        if (!warehouse.isPresent()) {
            throw new ItemNotFoundException("Warehouse not found");
        }
        if (!client.isPresent()) {
            throw new ItemNotFoundException("Client not found");
        }
        if (!currency.isPresent()) {
            throw new ItemNotFoundException("Currency not found");
        }
        Output output = outputRepositoryById.get();
        output.setWarehouse(warehouse.get());
        output.setClient(client.get());
        output.setCurrency(currency.get());
        output.setCode(dto.getCode());
        output.setDate(Timestamp.valueOf(LocalDateTime.now()));
        output.setFactureNumber(dto.getFactureNumber());
        outputRepository.save(output);
        return new ResultDTO("Output successfully update", true);

    }

    public ResultDTO deleteOutput(Integer id) {
        Optional<Output> outputRepositoryById = outputRepository.findById(id);
        if (!outputRepositoryById.isPresent()) {
            throw new ItemNotFoundException("Output not found");
        }
        Output output = outputRepositoryById.get();
        output.setActive(false);
        outputRepository.save(output);
        return new ResultDTO("Output delete", true);
    }

    public Set<OutputResponseDTO> listOfOutput() {
        Iterable<Output> outputs = outputRepository.findAll();
        if (outputs == null) {
            throw new ItemNotFoundException("List empty");
        }
        Set<OutputResponseDTO> outputResponse = new HashSet<>();
        outputs.forEach(output -> {
            OutputResponseDTO dto = new OutputResponseDTO();
            dto.setId(output.getId());
            dto.setWarehouse(output.getWarehouse());
            dto.setClient(output.getClient());
            dto.setCurrency(output.getCurrency());
            dto.setCode(output.getCode());
            dto.setFactureNumber(output.getFactureNumber());
            dto.setDate(output.getDate());
            outputResponse.add(dto);
        });
        return outputResponse;
    }
}
