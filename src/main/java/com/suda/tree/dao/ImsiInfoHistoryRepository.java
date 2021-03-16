package com.suda.tree.dao;

import com.suda.tree.entity.mysql.ImsiInfo;
import com.suda.tree.entity.mysql.ImsiInfoHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImsiInfoHistoryRepository extends JpaRepository<ImsiInfoHistory, Long> {

    @Override
    <S extends ImsiInfoHistory> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends ImsiInfoHistory> S save(S s);

    @Override
    List<ImsiInfoHistory> findAll();

    @Override
    Optional<ImsiInfoHistory> findById(Long s);

    Page<ImsiInfoHistory> findByImsi(String imsi , Pageable pageable);

    Page<ImsiInfoHistory> findByImsiOrderBySendTimeDesc(String imsi , Pageable pageable);
}

