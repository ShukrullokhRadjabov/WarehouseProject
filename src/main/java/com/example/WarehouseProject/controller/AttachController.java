package com.example.WarehouseProject.controller;

import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.service.AttachService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("private/upload")
    public ResultDTO upload(MultipartRequest multipartRequest) throws IOException {
        ResultDTO resultDTO = attachService.upload(multipartRequest);
        return resultDTO;
    }
}
