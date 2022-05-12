package com.example.restblog.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.restblog.config.S3Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// from https://www.youtube.com/watch?v=vY7c7k8xmKE

@Service
@Slf4j
public class S3Service {
    @Value("${aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 s3Client;

    public byte[] downloadFile(String fileName) {
        S3Object s3o = s3Client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3o.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucket, fileName);
        return fileName + " deleted";
    }

    public String uploadFile(MultipartFile file) {
        File convertedFile = convertMultipartFileToFile(file);
        String fileName = file.getOriginalFilename();
        s3Client.putObject(bucket, fileName, convertedFile);
        convertedFile.delete();
        return fileName + " uploaded";
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return convertedFile;
    }
}
