package com.suda.tree.service;

import com.suda.tree.entity.mysql.TreeInfo;

public interface TreeInfoService {

    TreeInfo getTreeInfoByTreeId(String treeId);

    TreeInfo saveTreeInfo(TreeInfo treeInfo, String username);
}
