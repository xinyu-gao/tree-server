package com.suda.tree.service;

import com.suda.tree.entity.mysql.TreeGradeStatistic;
import com.suda.tree.entity.mysql.TreeInfo;

import java.util.List;

public interface TreeInfoService {

    TreeInfo getTreeInfoByTreeId(String treeId);

    TreeInfo saveTreeInfo(TreeInfo treeInfo, String username);

    List<TreeGradeStatistic> calculateStatistic();
}
