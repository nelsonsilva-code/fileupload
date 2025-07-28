package com.nelson.fileupload.service.impl;

import com.nelson.fileupload.service.UploadFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@AllArgsConstructor
@Slf4j
public class UploadFileServiceImpl implements UploadFileService {

    private final S3Client s3Client;

    @Value("${aws.bucket}")
    private String bucketName;

    @Autowired
    public UploadFileServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(String key, byte[] content) {
        System.out.println("print4");

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();

        System.out.println("print5");
        System.out.println(putObjectRequest);
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content));
        System.out.println("print6");
        return String.format("%s/%s/%s", s3Client.serviceClientConfiguration().endpointProvider(), bucketName , key);
    }
}
