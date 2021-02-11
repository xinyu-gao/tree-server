package com.suda.tree.service;

import com.suda.tree.entity.mysql.TreeGradeStatistic;

import java.util.List;

public interface StatisticService {

    TreeGradeStatistic getTreeGrade(String province);

    TreeGradeStatistic saveTreeGrade(TreeGradeStatistic treeGradeStatistic);

    List<TreeGradeStatistic> saveAll(List<TreeGradeStatistic> treeGradeStatisticList);
}
