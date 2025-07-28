package com.nelson.fileupload.controller;


import com.nelson.fileupload.service.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/file")
@AllArgsConstructor
public class FileUploadController {

    private UploadFileService uploadFileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String url = uploadFileService.uploadFile(fileName, file.getBytes());
        return ResponseEntity.ok(url);
    }
}