package com.suda.tree.dao;

import com.suda.tree.entity.mysql.WarnThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface WarnThresholdRepository extends JpaRepository<WarnThreshold, Long> {

    @Override
    Optional<WarnThreshold> findById(Long id);

    @Override
    <S extends WarnThreshold> S save(S s);
}
