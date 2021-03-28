package com.suda.tree.dao;

import com.suda.tree.entity.mysql.ExistedProvinceAndCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExistedProvinceAndCityRepository extends JpaRepository<ExistedProvinceAndCity, String> {

    @Override
    ExistedProvinceAndCity save(ExistedProvinceAndCity existedProvinceAndCity);

    @Override
    <S extends ExistedProvinceAndCity> List<S> saveAll(Iterable<S> iterable);
}
