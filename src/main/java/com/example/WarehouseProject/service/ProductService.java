package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.ProductDTO;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.entity.AttachEntity;
import com.example.WarehouseProject.entity.CategoryEntity;
import com.example.WarehouseProject.entity.MeasurementEntity;
import com.example.WarehouseProject.entity.ProductEntity;
import com.example.WarehouseProject.exceptions.AlreadyExistException;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.AttachRepository;
import com.example.WarehouseProject.repository.CategoryRepository;
import com.example.WarehouseProject.repository.MeasurementRepository;
import com.example.WarehouseProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttachRepository attachRepository;
    @Autowired
    private MeasurementRepository measurementRepository;

    public ResultDTO addProduct(ProductDTO productDTO) {
        Boolean result = productRepository.existsByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());
        if (result) {
            throw new AlreadyExistException("This product already exists in this category");
        }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByIdAndActive(productDTO.getCategoryId(), true);
        if (!optionalCategory.isPresent()) {
            throw new ItemNotFoundException("Such category not found");
        }
        Optional<AttachEntity> optionalPhoto = attachRepository.findById(productDTO.getPhotoId());
        if (!optionalPhoto.isPresent()) {
            throw new ItemNotFoundException("Attach not found");
        }
        Optional<MeasurementEntity> optionalMeasurement = measurementRepository.findByIdAndActive(productDTO.getMeasurementId(), true);
        if (!optionalMeasurement.isPresent()) {
            throw new ItemNotFoundException("Measurement not found");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setCode(UUID.randomUUID().toString());
        productEntity.setCategory(optionalCategory.get());
        productEntity.setPhoto(optionalPhoto.get());
        productEntity.setMeasurement(optionalMeasurement.get());
        productRepository.save(productEntity);
        return new ResultDTO("Product successfully added", true);
    }

    public ResultDTO updateProduct(ProductDTO productDTO, Integer id) {
        Optional<ProductEntity> product = productRepository.findByIdAndActive(id, true);
        if (!product.isPresent()) {
            throw new ItemNotFoundException("Product bot found");
        }
        ProductEntity productEntity = product.get();
        if (!productDTO.getName().isEmpty()) {
            productEntity.setName(productDTO.getName());
        }
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByIdAndActive(productDTO.getCategoryId(), true);
        if (!optionalCategory.isPresent()) {
            throw new ItemNotFoundException("Such category not found");
        }
        Optional<AttachEntity> optionalPhoto = attachRepository.findById(productDTO.getPhotoId());
        if (!optionalPhoto.isPresent()) {
            throw new ItemNotFoundException("Attach not found");
        }
        Optional<MeasurementEntity> optionalMeasurement = measurementRepository.findByIdAndActive(productDTO.getMeasurementId(), true);
        if (!optionalMeasurement.isPresent()) {
            throw new ItemNotFoundException("Measurement not found");
        }
        productEntity.setPhoto(optionalPhoto.get());
        productEntity.setCategory(optionalCategory.get());
        productEntity.setMeasurement(optionalMeasurement.get());
        productRepository.save(productEntity);
        return new ResultDTO("Successfully added", true);
    }

    public ResultDTO deleteProduct(Integer id) {
        Optional<ProductEntity> byId = productRepository.findByIdAndActive(id, true);
        if (!byId.isPresent()) {
            return new ResultDTO("Product not found", false);
        }
        ProductEntity productEntity = byId.get();
        productEntity.setActive(Boolean.FALSE);
        productRepository.save(productEntity);
        return new ResultDTO("Product successfully delete", true);
    }

    public List<ProductDTO> listOfProduct() {
        Iterable<ProductEntity> all = productRepository.findAllByActive(true);
        List<ProductDTO> productDTOList = new LinkedList<>();
        all.forEach(productEntity -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(productEntity.getName());
            productDTO.setCategoryId(productEntity.getCategory().getId());
            productDTO.setMeasurementId(productEntity.getMeasurement().getId());
            productDTO.setPhotoId(productEntity.getPhoto().getId());
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }
}
