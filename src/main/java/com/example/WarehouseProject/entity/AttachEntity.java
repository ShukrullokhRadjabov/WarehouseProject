package com.example.WarehouseProject.entity;

import ch.qos.logback.core.joran.action.AppenderRefAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long size;
    private String contentType;
}
