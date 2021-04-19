package com.suda.tree.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface TreePicService {

    void upsert(String id, MultipartFile file, String username) throws Exception;

    void deleteByFileURL(String id, String fileName, String username) throws Exception;

    void deleteByFileName(String id, String fileName, String username) throws Exception;

    List<String> getPicturesById(String treeId);
}
