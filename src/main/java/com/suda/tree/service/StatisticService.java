package com.suda.tree.service;

import com.suda.tree.entity.mysql.TreeGradeStatistic;

public interface StatisticService {

    TreeGradeStatistic getTreeGrade(String province);

    TreeGradeStatistic saveTreeGrade(TreeGradeStatistic treeGradeStatistic);
}
