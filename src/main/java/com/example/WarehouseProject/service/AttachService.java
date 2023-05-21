package com.example.WarehouseProject.service;

import com.example.WarehouseProject.entity.AttachContentEntity;
import com.example.WarehouseProject.entity.AttachEntity;
import com.example.WarehouseProject.dto.ResultDTO;
import com.example.WarehouseProject.exceptions.ItemNotFoundException;
import com.example.WarehouseProject.repository.AttachContentRepository;
import com.example.WarehouseProject.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;
    @Autowired
    private AttachContentRepository attachContentRepository;
    public ResultDTO upload(MultipartRequest multipartRequest) throws IOException {
        Iterator<String> fileNames = multipartRequest.getFileNames();
        MultipartFile file = multipartRequest.getFile(fileNames.next());
        AttachEntity attach = new AttachEntity();
        attach.setName(file.getOriginalFilename());
        attach.setSize(file.getSize());
        attach.setContentType(file.getContentType());
        AttachEntity savedAttach = attachRepository.save(attach);
        AttachContentEntity attachContent = new AttachContentEntity();
        attachContent.setBytes(file.getBytes());
        attachContent.setAttach(savedAttach);
        attachContentRepository.save(attachContent);
        return new ResultDTO("File saved", true, savedAttach.getId());
    }

}
