package com.suda.tree.dao;

import com.suda.tree.entity.mongo.UploadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadHistoryRepository extends MongoRepository<UploadHistory, String> {

    @Override
    UploadHistory save(UploadHistory uploadHistory);
}
