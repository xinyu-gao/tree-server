package com.suda.tree.dao;

import com.suda.tree.entity.mongo.TreePicture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreePictureRepository extends MongoRepository<TreePicture, String> {

    Optional<TreePicture> findByTreeId(String id);

    TreePicture save(TreePicture treePicture);

    void deleteByTreeId(String id);
}
