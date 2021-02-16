package com.suda.tree.service;

import com.suda.tree.entity.mongo.ExistedProvinceAndCity;

import java.util.List;

public interface ExistedProvinceAndCityService {

    List<ExistedProvinceAndCity> saveExistedProvinceAndCityStatistic();

    List<ExistedProvinceAndCity> getExistedProvinceAndCityStatistic();
}
