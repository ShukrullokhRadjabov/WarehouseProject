package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.dto.SupplierResponseDTO;
import com.example.WarehouseProject.entity.ClientEntity;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public ResultDTO addClient(ClientEntity client){
        if(client.getPhoneNumber().isEmpty() || client.getPhoneNumber() == null){
            return new ResultDTO("Phone number empty", false);
        }
        if(clientRepository.existsByPhoneNumber(client.getPhoneNumber())){
            return new ResultDTO("This client already exist", false);
        }
        else {
            clientRepository.save(client);
            return new ResultDTO("New client successfully added", true);
        }
    }

    public ResultDTO updateClient(Integer id, ClientEntity client) {
        Optional<ClientEntity> optional = clientRepository.findById(id);
        ClientEntity entity = optional.get();
        if(!optional.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }
        if(!client.getName().isEmpty()){
            entity.setName(client.getName());
        }
        if(!client.getPhoneNumber().isEmpty()){
            entity.setPhoneNumber(client.getPhoneNumber());
        }
        clientRepository.save(entity);
        return new ResultDTO("Successfully update", true);
    }

    public ResultDTO deleteClient(Integer id) {
        Optional<ClientEntity> byId = clientRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }
        ClientEntity clientEntity = byId.get();
        clientEntity.setActive(false);
        clientRepository.save(clientEntity);
        return new ResultDTO("Successfully delete", true);
    }

    public List<SupplierResponseDTO> listOfClient() {
        List<ClientEntity> listOf = clientRepository.findAll();
        if(listOf.isEmpty()){
            throw new ItemNotFoundException("Item not found");
        }
        List<SupplierResponseDTO> dtoList = new LinkedList<>();
        for (ClientEntity clientEntity : listOf) {
            SupplierResponseDTO dto = new SupplierResponseDTO();
            dto.setId(clientEntity.getId());
            dto.setName(clientEntity.getName());
            dto.setActive(clientEntity.isActive());
            dto.setPhoneNumber(clientEntity.getPhoneNumber());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
