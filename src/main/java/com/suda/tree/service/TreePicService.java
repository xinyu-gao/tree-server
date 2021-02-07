package com.suda.tree.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface TreePicService {

    void upsert(String id, MultipartFile file, String username) throws Exception;

    void delete(String id, String fileName, String username) throws Exception;

    Set<String> getPicturesById(String treeId);
}
