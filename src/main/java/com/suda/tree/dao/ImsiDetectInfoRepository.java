package com.suda.tree.dao;

import com.suda.tree.entity.mysql.ImsiDetectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImsiDetectInfoRepository extends JpaRepository<ImsiDetectInfo, String> {

    @Override
    <S extends ImsiDetectInfo> S save(S s);

    @Override
    Optional<ImsiDetectInfo> findById(String s);
}
