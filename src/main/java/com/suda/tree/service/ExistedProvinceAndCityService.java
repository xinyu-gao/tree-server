package com.suda.tree.service;

import com.suda.tree.entity.mysql.ExistedProvinceAndCity;

import java.util.List;

public interface ExistedProvinceAndCityService {

    List<ExistedProvinceAndCity> saveExistedProvinceAndCityStatistic();

    List<ExistedProvinceAndCity> getExistedProvinceAndCityStatistic();
}
