package com.suda.tree.service.impl;

import com.suda.tree.dao.TreeInfoRepository;
import com.suda.tree.dao.UploadHistoryRepository;
import com.suda.tree.entity.mongo.UploadHistory;
import com.suda.tree.entity.mysql.TreeInfo;
import com.suda.tree.service.TreeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreeInfoServiceImpl implements TreeInfoService {
    @Autowired
    private TreeInfoRepository treeInfoRepository;
    @Autowired
    private UploadHistoryRepository uploadHistoryRepository;

    @Override
    public TreeInfo getTreeInfoByTreeId(String treeId) {
        Optional<TreeInfo> treeInfoOptional = treeInfoRepository.findByTreeId(treeId);
        return treeInfoOptional.orElseGet(TreeInfo::new);
    }

    @Override
    public TreeInfo saveTreeInfo(TreeInfo treeInfo, String username) {
        String detail = treeInfoRepository.findByTreeId(treeInfo.getTreeId()).isPresent() ? "修改数据" : "新增数据";
        UploadHistory uploadHistory = new UploadHistory(treeInfo.getTreeId(), username, detail);
        uploadHistoryRepository.save(uploadHistory);
        return treeInfoRepository.save(treeInfo);
    }
}
