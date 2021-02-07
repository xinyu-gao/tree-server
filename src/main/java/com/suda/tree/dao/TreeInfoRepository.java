package com.suda.tree.dao;

import com.suda.tree.entity.mysql.TreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreeInfoRepository extends JpaRepository<TreeInfo, String> {

    TreeInfo save(TreeInfo tree);

    Optional<TreeInfo> findByTreeId(String treeId);
}
