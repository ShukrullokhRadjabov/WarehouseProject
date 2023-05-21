package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.AuthDTO;
import com.example.WarehouseProject.dto.AuthResponseDTO;
import com.example.WarehouseProject.entity.UserEntity;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.UserRepository;
import com.example.WarehouseProject.util.JwtUtil;
import com.example.WarehouseProject.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public AuthResponseDTO login(AuthDTO dto){
        Optional<UserEntity> optional = userRepository.findByPhoneNumberAndPassword(dto.getPhone(), MD5Util.getMd5Hash(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Phone or password incorrect");
        }
        UserEntity entity = optional.get();
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setName(entity.getFirstName());
        authResponseDTO.setSurname(entity.getLastName());
        authResponseDTO.setRole(entity.getRole());
        authResponseDTO.setJwt(JwtUtil.encode(entity.getPhoneNumber(), entity.getRole()));
        return authResponseDTO;
    }
}
