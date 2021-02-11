package com.suda.tree.dao;

import com.suda.tree.dto.GradeStatisticSQLResult;
import com.suda.tree.entity.mysql.TreeGradeStatistic;
import com.suda.tree.entity.mysql.TreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreeInfoRepository extends JpaRepository<TreeInfo, String> {

    TreeInfo save(TreeInfo tree);

    Optional<TreeInfo> findByTreeId(String treeId);

    @Query(nativeQuery = true, value ="select location_province as province, grade, count(*) as count from tree_info group by location_province, grade order by location_province")
    List<Object[]> getTreeGradeStatistic();

}
