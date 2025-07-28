package com.nelson.fileupload.service.impl;

import com.nelson.fileupload.exception.FileUploadFailed;
import com.nelson.fileupload.security.CustomUserDetailsService;
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

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content));
            String objectPath = s3Client.serviceClientConfiguration().endpointProvider() + "/" + bucketName + "/" + key;
            log.info(CustomUserDetailsService.getCurrentUser() + " uploaded " + objectPath);
            return objectPath;

        } catch (Exception e) {
            log.error("AWS access key: " + System.getenv("AWS_ACCESS_KEY_ID"));
            log.error("S3 Region: " + s3Client.serviceClientConfiguration().region());
            log.error("AWS Enpoint: " + s3Client);
            log.error("putObjectRequest: " + putObjectRequest);
            log.error("Content length: " + (content != null ? content.length : "null"));
            log.error("Upload failed ", e);
            throw new FileUploadFailed();
        }
    }
}
