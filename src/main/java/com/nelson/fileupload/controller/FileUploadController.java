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
        System.out.println("print1");
        String fileName = file.getOriginalFilename();
        System.out.println("print2");
        String url = uploadFileService.uploadFile(fileName, file.getBytes());
        System.out.println("print3");

        return ResponseEntity.ok(url);
    }
}