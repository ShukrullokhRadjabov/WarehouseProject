package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.AuthDTO;
import com.example.WarehouseProject.dto.AuthResponseDTO;
import com.example.WarehouseProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/public")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
