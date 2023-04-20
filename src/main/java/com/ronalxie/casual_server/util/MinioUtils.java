package com.ronalxie.casual_server.util;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MinioUtils {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.accessKey}")
    private String accessKey;

    private MinioClient minioClient;

    public MinioUtils(){

    }

    public String upload(MultipartFile file,String type) throws IOException {
        System.out.println(endpoint);
        minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();

        if (ObjectUtils.isEmpty(type)){
            throw new RuntimeException("类型为空！");
        }
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("\\.");
        if (split.length > 1) {
            fileName = split[0] + "_" + System.currentTimeMillis() + "." + split[1];
        } else {
            fileName = fileName + System.currentTimeMillis();
        }
        InputStream in = null;
        try {
            in = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(type+"/"+fileName)
                    .stream(in, in.available(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                in.close();
            }
        }
        return endpoint+"/"+bucket+"/"+type+"/"+fileName;

    }

}
