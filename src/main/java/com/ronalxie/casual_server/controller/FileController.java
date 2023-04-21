package com.ronalxie.casual_server.controller;

import com.ronalxie.casual_server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload/{type}",method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("type") String type){
        return fileService.upload(multipartFile,type);
    }
}
