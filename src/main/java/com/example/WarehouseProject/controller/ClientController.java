package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.dto.SupplierResponseDTO;
import com.example.WarehouseProject.entity.ClientEntity;
import com.example.WarehouseProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping("/private/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO addClient(@RequestBody ClientEntity client){
        return clientService.addClient(client);
    }

    @PutMapping("/private/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO updateClient(@PathVariable("id") Integer id, @RequestBody ClientEntity client){
        return clientService.updateClient(id, client);
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteClient(@PathVariable("id") Integer id){
        return clientService.deleteClient(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SupplierResponseDTO> listOfClient(){
        return clientService.listOfClient();
    }
}
