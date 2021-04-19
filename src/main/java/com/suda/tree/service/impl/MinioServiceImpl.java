package com.suda.tree.service.impl;

import java.time.LocalDate;

import cn.hutool.core.util.StrUtil;
import com.suda.tree.service.MinioService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.port}")
    private String port;

    @Value("${minio.bucket}")
    private String bucketName;


    @Autowired
    private MinioClient minioClient;


    /**
     * 上传文件
     */
    public String putObject(String id, MultipartFile multipartFile) throws Exception {
        if (!isBucketExists()) setNewBucket(bucketName);
        String filename = multipartFile.getOriginalFilename();
        // 存储对象名称
        String objectName = getObjectName(id, filename);
        // 使用putObject上传一个文件到存储桶中
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                        .contentType(multipartFile.getContentType())
                        .build());
        return getFileURL(id, objectName);
    }

    public void copy(String source, String target) {
        try {
            minioClient.copyObject(CopyObjectArgs.builder()
                    .bucket(bucketName)
                    .object(target)
                    .source(CopySource.builder()
                            .bucket(bucketName)
                            .object(source)
                            .build())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id, String fileName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(getObjectName(id, fileName))
                            .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回图片网址，示例：http://175.24.147.229:9090/tree/1234/picture1.png
     */
    public String getFileURL(String id, String fileName) {
        return getURLPrefix() + getObjectName(id, fileName);
    }

    public String getFileURL(String id, MultipartFile file) {
        return getURLPrefix() + getObjectName(id, file.getOriginalFilename());
    }

    public String getFileNameFromURL(String id, String url) {
        return StrUtil.removePrefix(url, getURLPrefix() + id);
    }


    /**
     * 返回存储对象名，示例：1234/picture1.png
     */
    public String getObjectName(String id, String fileName) {
        return id + "/" + fileName;
    }

    private String getURLPrefix() {
        return "http://" + endpoint + ":" + port + "/" + bucketName + "/";
    }


    /**
     * 判断桶是否存在
     */
    private boolean isBucketExists() throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 新建存储桶
     */
    private void setNewBucket(String bucketName) throws Exception {
        minioClient.makeBucket(
                MakeBucketArgs.builder()
                        .bucket(bucketName).build());
    }

    /**
     * 获取文件后缀
     */
    private static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            String suffix = fileName.substring(index + 1);
            if (!suffix.isEmpty()) {
                return suffix;
            }
        }
        throw new IllegalArgumentException("非法文件名称：" + fileName);
    }

    /**
     * 获取年月日[2021, 01, 01]
     */
    private static String[] getDateFolder() {
        String[] retVal = new String[3];
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        retVal[0] = localDate.getYear() + "";
        retVal[1] = month < 10 ? "0" + month : month + "";
        retVal[2] = day < 10 ? "0" + day : day + "";

        return retVal;
    }

}