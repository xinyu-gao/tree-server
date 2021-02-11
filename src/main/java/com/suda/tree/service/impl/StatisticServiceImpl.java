package com.suda.tree.service.impl;

import com.suda.tree.dao.TreeGradeRepository;
import com.suda.tree.entity.mysql.TreeGradeStatistic;
import com.suda.tree.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private TreeGradeRepository treeGradeRepository;

    @Override
    public TreeGradeStatistic getTreeGrade(String province) {
        Optional<TreeGradeStatistic> treeGrade = treeGradeRepository.findByProvince(province);
        return treeGrade.orElseGet(TreeGradeStatistic::new);
    }

    @Override
    public TreeGradeStatistic saveTreeGrade(TreeGradeStatistic treeGradeStatistic) {
        return treeGradeRepository.save(treeGradeStatistic);
    }
    @Override
    public List<TreeGradeStatistic> saveAll(List<TreeGradeStatistic> treeGradeStatisticList){
        return treeGradeRepository.saveAll(treeGradeStatisticList);
    }
}
