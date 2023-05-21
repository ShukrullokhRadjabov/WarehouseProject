package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.*;
import com.example.WarehouseProject.entity.*;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InputProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private InputProductRepository inputProductRepository;
    public ResultDTO addInputProduct(InputProductRequestDTO dto) {
        Optional<ProductEntity> product = productRepository.findById(dto.getProductId());
        Optional<Input> input = inputRepository.findById(dto.getInputId());
        if (!product.isPresent()) {
            throw new ItemNotFoundException("Entity not found");
        }
        if (!input.isPresent()) {
            throw new ItemNotFoundException("Input id not found");
        }
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(product.get());
        inputProduct.setInput(input.get());
        inputProduct.setAmount(dto.getAmount());
        inputProduct.setPrice(dto.getPrice());
        inputProduct.setExpireDate(LocalDate.now().plusDays(dto.getDay()));
        inputProductRepository.save(inputProduct);
        return new ResultDTO("InputProduct successfully saved", true);
    }

    public ResultDTO updateInputProduct(InputProductRequestDTO dto, Integer id) {
        Optional<InputProduct> inputProductRepositoryById = inputProductRepository.findById(id);
        if (!inputProductRepositoryById.isPresent()) {
            throw new ItemNotFoundException("InputProduct not found");
        }
        Optional<ProductEntity> product = productRepository.findByIdAndActive(dto.getProductId(), true);
        Optional<Input> input1 = inputRepository.findByIdAndActive(dto.getInputId(), true);

        if (!product.isPresent()) {
            throw new ItemNotFoundException("Product not found");
        }
        if (!input1.isPresent()) {
            throw new ItemNotFoundException("Input not found");
        }
        InputProduct inputProduct = inputProductRepositoryById.get();
        inputProduct.setProduct(product.get());
        inputProduct.setInput(input1.get());
        inputProduct.setAmount(dto.getAmount());
        inputProduct.setPrice(dto.getPrice());
        inputProduct.setExpireDate(LocalDate.now().plusDays(dto.getDay()));
        inputProductRepository.save(inputProduct);
        return new ResultDTO("InputProduct successfully update", true);
    }

    public ResultDTO deleteInputProduct(Integer id) {
        Optional<InputProduct> inputProductRepositoryById = inputProductRepository.findByIdAndActive(id, true);
        if (!inputProductRepositoryById.isPresent()) {
            throw new ItemNotFoundException("Input Product not found");
        }
        InputProduct inputProduct = inputProductRepositoryById.get();
        inputProduct.setActive(false);
        inputProductRepository.save(inputProduct);
        return new ResultDTO("InputProduct delete", true);
    }

    public Set<InputProductResponseDTO> listOfInputProduct() {
        Iterable<InputProduct> inputProducts = inputProductRepository.findAllByActive(true);
        if (inputProducts == null) {
            throw new ItemNotFoundException("Input Product List empty");
        }
        Set<InputProductResponseDTO> inputProductResponse = new HashSet<>();
        inputProducts.forEach(inputProduct -> {
            InputProductResponseDTO dto = new InputProductResponseDTO();
            dto.setProduct(inputProduct.getProduct());
            dto.setInput(inputProduct.getInput());
            dto.setAmount(inputProduct.getAmount());
            dto.setPrice(inputProduct.getPrice());
            dto.setId(inputProduct.getId());
            dto.setDate(inputProduct.getExpireDate());
            inputProductResponse.add(dto);
        });
        return inputProductResponse;
    }
}