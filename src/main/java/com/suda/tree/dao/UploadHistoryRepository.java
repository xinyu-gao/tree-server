package com.suda.tree.dao;

import com.suda.tree.entity.mysql.UploadHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadHistoryRepository extends JpaRepository<UploadHistory, String> {

    @Override
    UploadHistory save(UploadHistory uploadHistory);

    @Override
    List<UploadHistory> findAll();
}
