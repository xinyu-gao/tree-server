package com.suda.tree.dao;

import com.suda.tree.entity.mysql.ImsiDetectInfo;
import com.suda.tree.entity.mysql.ImsiDetectInfoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImsiDetectInfoHistoryRepository extends JpaRepository<ImsiDetectInfoHistory, Long> {

    @Override
    <S extends ImsiDetectInfoHistory> S save(S s);

    List<ImsiDetectInfoHistory> findAllByImsi(String imsi);
}
