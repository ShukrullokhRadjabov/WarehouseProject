package com.example.WarehouseProject.entity;
import com.example.WarehouseProject.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity extends AbstractEntity {
    @ManyToOne
    private CategoryEntity parentCategory;
}
