package com.ronalxie.casual_server.service.impl;

import com.ronalxie.casual_server.service.FileService;
import com.ronalxie.casual_server.util.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioUtils minioUtils;
    @Override
    public String upload(MultipartFile multipartFile, String type) {
        try {
            return minioUtils.upload(multipartFile,type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
