package com.suda.tree.dao;

import com.suda.tree.entity.mysql.TreeGradeStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeGradeRepository extends JpaRepository<TreeGradeStatistic, String> {

    TreeGradeStatistic save(TreeGradeStatistic treeGradeStatistic);

//    List<TreeGradeStatistic> saveAll(List<TreeGradeStatistic> treeGradeStatisticList);


    @Override
    <S extends TreeGradeStatistic> List<S> saveAll(Iterable<S> iterable);

    Optional<TreeGradeStatistic> findByProvince(String province);
}
