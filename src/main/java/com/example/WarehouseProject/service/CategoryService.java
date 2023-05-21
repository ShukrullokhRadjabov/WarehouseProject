package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.CategoryDTO;
import com.example.WarehouseProject.entity.AttachEntity;
import com.example.WarehouseProject.entity.CategoryEntity;
import com.example.WarehouseProject.dto.CategoryDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.MeasurementEntity;
import com.example.WarehouseProject.entity.ProductEntity;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public ResultDTO create(CategoryDTO categoryDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDTO.getName());
        if(categoryDTO.getParentCategoryId() != null) {
            Optional<CategoryEntity> parent = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (!parent.isPresent())
                return new ResultDTO("There are no such parent category", false);
            category.setParentCategory(parent.get());
        }
        categoryRepository.save(category);
        return new ResultDTO("Successfully saved", true);
    }
    public ResultDTO updateCategory(CategoryDTO categoryDTO, Integer id) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            return new ResultDTO("Category not found", false);
        }
        Optional<CategoryEntity> parent = categoryRepository.findById(categoryDTO.getParentCategoryId());
        if (!parent.isPresent()){
            throw new ItemNotFoundException("Parent category not found");
        }
        CategoryEntity categoryEntity = category.get();
        if (!categoryDTO.getName().isEmpty()) {
            categoryEntity.setName(categoryDTO.getName());
        }
        categoryEntity.setParentCategory(parent.get());
        categoryRepository.save(categoryEntity);
        return new ResultDTO("Successfully added", true);
    }

    public ResultDTO deleteCategory(Integer id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (!byId.isPresent()) {
            return new ResultDTO("Category not found", false);
        }
        CategoryEntity categoryEntity = byId.get();
        categoryEntity.setActive(Boolean.FALSE);
        categoryRepository.save(categoryEntity);
        return new ResultDTO("Category successfully delete", true);
    }

    public List<CategoryDTO> listOfCategory() {
        Iterable<CategoryEntity> all = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new LinkedList<>();
        all.forEach(categoryEntity -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(categoryEntity.getName());
            if(categoryEntity.getParentCategory() == null){
                categoryDTO.setParentCategoryId(null);
            }else {
                categoryDTO.setParentCategoryId(categoryEntity.getParentCategory().getId());
            }
            categoryDTOList.add(categoryDTO);
        });
        return categoryDTOList;
    }
}
