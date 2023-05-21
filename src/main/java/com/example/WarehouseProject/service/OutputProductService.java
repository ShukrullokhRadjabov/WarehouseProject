package com.example.WarehouseProject.service;

import com.example.WarehouseProject.dto.*;
import com.example.WarehouseProject.entity.*;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OutputProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private OutputProductRepository outputProductRepository;
    public ResultDTO addOutputProduct(OutputProductRequestDTO dto) {
        Optional<ProductEntity> product = productRepository.findById(dto.getProductId());
        Optional<Output> output = outputRepository.findById(dto.getOutputId());
        if (!product.isPresent()) {
            throw new ItemNotFoundException("Entity not found");
        }
        if (!output.isPresent()) {
            throw new ItemNotFoundException("Output id not found");
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(product.get());
        outputProduct.setOutput(output.get());
        outputProduct.setAmount(dto.getAmount());
        outputProduct.setPrice(dto.getPrice());
        outputProductRepository.save(outputProduct);
        return new ResultDTO("OutputProduct successfully saved", true);
    }

    public ResultDTO updateOutputProduct(OutputProductRequestDTO dto, Integer id) {
        Optional<OutputProduct> outputProductRepositoryById = outputProductRepository.findById(id);
        if (!outputProductRepositoryById.isPresent()) {
            throw new ItemNotFoundException("OutputProduct not found");
        }
        Optional<ProductEntity> product = productRepository.findByIdAndActive(dto.getProductId(), true);
        Optional<Output> output1 = outputRepository.findByIdAndActive(dto.getOutputId(), true);

        if (!product.isPresent()) {
            throw new ItemNotFoundException("Product not found");
        }
        if (!output1.isPresent()) {
            throw new ItemNotFoundException("Output not found");
        }
        OutputProduct outputProduct = outputProductRepositoryById.get();
        outputProduct.setProduct(product.get());
        outputProduct.setOutput(output1.get());
        outputProduct.setAmount(dto.getAmount());
        outputProduct.setPrice(dto.getPrice());
        outputProductRepository.save(outputProduct);
        return new ResultDTO("OutputProduct successfully update", true);
    }

    public ResultDTO deleteOutputProduct(Integer id) {
        Optional<OutputProduct> outputProductRepositoryById = outputProductRepository.findByIdAndActive(id, true);
        if (!outputProductRepositoryById.isPresent()) {
            throw new ItemNotFoundException("Output Product not found");
        }
        OutputProduct outputProduct = outputProductRepositoryById.get();
        outputProduct.setActive(false);
        outputProductRepository.save(outputProduct);
        return new ResultDTO("OutputProduct delete", true);
    }

    public Set<OutputProductResponseDTO> listOfOutputProduct() {
        Iterable<OutputProduct> outputProducts = outputProductRepository.findAllByActive(true);
        if (outputProducts == null) {
            throw new ItemNotFoundException("Output Product List empty");
        }
        Set<OutputProductResponseDTO> outputProductResponse = new HashSet<>();
        outputProducts.forEach(outputProduct -> {
            OutputProductResponseDTO dto = new OutputProductResponseDTO();
            dto.setProduct(outputProduct.getProduct());
            dto.setOutput(outputProduct.getOutput());
            dto.setAmount(outputProduct.getAmount());
            dto.setPrice(outputProduct.getPrice());
            dto.setId(outputProduct.getId());
            outputProductResponse.add(dto);
        });
        return outputProductResponse;
    }
}