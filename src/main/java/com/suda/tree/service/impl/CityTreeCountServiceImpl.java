package com.suda.tree.service.impl;

import com.suda.tree.dao.CityTreeCountRepository;
import com.suda.tree.entity.mysql.CityTreeCount;
import com.suda.tree.service.CityTreeCountService;
import com.suda.tree.service.TreeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityTreeCountServiceImpl implements CityTreeCountService {

    @Autowired
    private CityTreeCountRepository cityTreeCountRepository;

    @Autowired
    private TreeInfoService treeInfoService;

    @Override
    public List<CityTreeCount> saveCityTreeCount(){
        List<CityTreeCount> list = treeInfoService.genProvinceAndCityTreeCount();
        return cityTreeCountRepository.saveAll(list);
    }
}
