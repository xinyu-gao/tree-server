package com.suda.tree.service.impl;

import com.suda.tree.dao.TreePictureRepository;
import com.suda.tree.dao.UploadHistoryRepository;
import com.suda.tree.entity.mysql.TreePicture;
import com.suda.tree.entity.mysql.UploadHistory;
import com.suda.tree.service.MinioService;
import com.suda.tree.service.TreePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class TreePicServiceImpl implements TreePicService {

    @Autowired
    private TreePictureRepository treePictureRepository;

    @Autowired
    private MinioService minioService;

    @Autowired
    private UploadHistoryRepository uploadHistoryRepository;

    /**
     * 根据树木 id 添加图片，图片保存在 minio 中，URL保存在 MongoDB 中
     * （如果 id 不存在则自动创建）
     */
    @Override
    public void upsert(String id,  MultipartFile file, String username) throws Exception {
        if (null == file.getOriginalFilename()) {
            throw new Exception("file 不能为空");
        }
        String url = minioService.getFileURL(id, file);
        Optional<TreePicture> treePictureOptional = treePictureRepository.findById(id);
        if (treePictureOptional.isPresent()) {
            TreePicture treePicture = treePictureOptional.get();
            List<String> urls = treePicture.getPicUrls();
            if (urls.contains(url)) {
                throw new Exception("该图片 URL 已存在");
            }
            urls.add(url);
            minioService.putObject(id, file);
            treePicture.setPicUrls(urls);
            treePictureRepository.save(treePicture);
        } else {
            minioService.putObject(id, file);
            save(id, url);
        }
        UploadHistory uploadHistory = new UploadHistory(id, username, "增加图片");
        uploadHistoryRepository.save(uploadHistory);
    }

    @Override
    public List<String> getPicturesById(String treeId){
        Optional<TreePicture> pictures = treePictureRepository.findByTreeId(treeId);
        return pictures.map(TreePicture::getPicUrls).orElse(null);

    }


    @Override
    public void delete(String id, String fileName, String username) throws Exception {
        String url = minioService.getFileURL(id, fileName);
        Optional<TreePicture> treePictureOptional = treePictureRepository.findById(id);
        if (treePictureOptional.isPresent()) {
            TreePicture treePicture = treePictureOptional.get();
            List<String> urls = treePicture.getPicUrls();
            if (urls.contains(url)) {
                throw new Exception("该图片 URL 已存在");
            }
            urls.remove(url);
            minioService.delete(id, fileName);
            save(id, urls);
        } else {
            throw new Exception("id不存在");
        }
        UploadHistory uploadHistory = new UploadHistory(id, username, "删除图片");
        uploadHistoryRepository.save(uploadHistory);
    }

    private void save(String id, String picUrl) {
        treePictureRepository.save(new TreePicture(id, Collections.singletonList(picUrl)));
    }

    private void save(String id, List<String> picUrls) {
        treePictureRepository.save(new TreePicture(id, picUrls));
    }
}
