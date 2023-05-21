package com.example.WarehouseProject.entity;

import com.example.WarehouseProject.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String code;
    @Column(nullable = false)
    private String password;
    private Boolean active = Boolean.TRUE;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
