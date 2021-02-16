package com.suda.tree.dao;

import com.suda.tree.entity.mongo.ExistedProvinceAndCity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExistedProvinceAndCityRepository extends MongoRepository<ExistedProvinceAndCity, String> {

    @Override
    ExistedProvinceAndCity save(ExistedProvinceAndCity existedProvinceAndCity);

    @Override
    <S extends ExistedProvinceAndCity> List<S> saveAll(Iterable<S> iterable);
}
