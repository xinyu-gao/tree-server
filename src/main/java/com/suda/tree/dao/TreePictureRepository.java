package com.suda.tree.dao;

import com.suda.tree.entity.mysql.TreePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreePictureRepository extends JpaRepository<TreePicture, String> {

    Optional<TreePicture> findByTreeId(String id);

    TreePicture save(TreePicture treePicture);

    void deleteByTreeId(String id);
}
