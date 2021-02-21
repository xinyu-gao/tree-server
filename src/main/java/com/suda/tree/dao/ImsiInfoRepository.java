package com.suda.tree.dao;

import com.suda.tree.entity.mysql.ImsiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImsiInfoRepository extends JpaRepository<ImsiInfo, String> {

    @Override
    <S extends ImsiInfo> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends ImsiInfo> S save(S s);

    @Override
    List<ImsiInfo> findAll();

    @Override
    Optional<ImsiInfo> findById(String s);

    Optional<ImsiInfo> findByImsi(String s);
}
