package com.ronalxie.casual_server.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ronalxie.casual_server.entity.FileDo;
import com.ronalxie.casual_server.mapper.FileDoMapper;
import com.ronalxie.casual_server.service.FileService;
import com.ronalxie.casual_server.util.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDoMapper fileDoMapper;


    @Autowired
    private MinioUtils minioUtils;
    @Override
    public String upload(MultipartFile multipartFile, String type) {
        try {
            FileDo uploadFileDo = minioUtils.upload(multipartFile, type);
            uploadFileDo.setSid(IdUtil.getSnowflakeNextId());
            fileDoMapper.insert(uploadFileDo);
            return uploadFileDo.getUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
