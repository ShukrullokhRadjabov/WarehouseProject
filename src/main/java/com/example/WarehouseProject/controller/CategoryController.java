package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.CategoryDTO;
import com.example.WarehouseProject.dto.ProductDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/private/create")
    public ResultDTO create(@RequestBody CategoryDTO categoryDTO){
        ResultDTO resultDTO = categoryService.create(categoryDTO);
        return resultDTO;
    }

    @PutMapping("/private/update/{id}")
    public ResultDTO updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryDTO categoryDTO){
        ResultDTO resultDTO = categoryService.updateCategory(categoryDTO, id);
        return resultDTO;
    }


    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultDTO deleteCurrency(@PathVariable("id") Integer id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/private/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryDTO> listOfCurrency(){
        return categoryService.listOfCategory();
    }
}
