package com.ronalxie.casual_server.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String upload(MultipartFile multipartFile,String type);
}
