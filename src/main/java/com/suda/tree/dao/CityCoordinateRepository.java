package com.suda.tree.dao;

import com.suda.tree.entity.mysql.CityCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityCoordinateRepository  extends JpaRepository<CityCoordinate, String> {

    @Override
    <S extends CityCoordinate> List<S> saveAll(Iterable<S> iterable);

    @Override
    List<CityCoordinate> findAll();
}
