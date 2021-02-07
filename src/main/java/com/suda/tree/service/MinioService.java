package com.suda.tree.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MinioService {
    /**
     * 上传文件
     */
    String putObject(String id, MultipartFile multipartFile) throws Exception;

    /**
     * 复制文件
     */
    void copy(String source, String target);

    /**
     * 删除文件
     */
    void delete(String id, String fileName);

    /**
     * 获取文件 URL
     */
    String getFileURL(String id, String fileName);

    String getFileURL(String id, MultipartFile file);

    /**
     * 获取对象名
     */
    String getObjectName(String id, String fileName);

}
