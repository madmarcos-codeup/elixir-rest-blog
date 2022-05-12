package com.example.restblog.web;

import com.example.restblog.services.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/s3", headers = "Accept=application/json")
@Slf4j
public class S3Controller {
    private S3Service s3Service;

    @GetMapping("download/{fileName}")
    public ResponseEntity<ByteArrayResource> getByFilename(@PathVariable String fileName) {
        byte [] data = s3Service.downloadFile(fileName);
        ByteArrayResource bytes = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(bytes);
    }

    @PostMapping("upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(s3Service.uploadFile(file), HttpStatus.OK);
    }


}
