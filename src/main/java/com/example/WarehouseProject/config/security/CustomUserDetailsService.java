package com.example.WarehouseProject.config.security;


import com.example.WarehouseProject.entity.UserEntity;
import com.example.WarehouseProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<UserEntity> optional = userRepository.findByPhoneNumber(phone);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        UserEntity user = optional.get();
        return new CustomUserDetails(user);
    }
}
