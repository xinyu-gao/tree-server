package com.suda.tree.service.impl;

import com.suda.tree.dao.ExistedProvinceAndCityRepository;
import com.suda.tree.dao.TreeInfoRepository;
import com.suda.tree.entity.mysql.ExistedProvinceAndCity;
import com.suda.tree.service.ExistedProvinceAndCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExistedProvinceAndCityServiceImpl implements ExistedProvinceAndCityService {

    @Autowired
    private ExistedProvinceAndCityRepository existedProvinceAndCityRepository;

    @Autowired
    private TreeInfoRepository treeInfoRepository;

    @Override
    public List<ExistedProvinceAndCity> saveExistedProvinceAndCityStatistic() {
        List<Object[]> infos = treeInfoRepository.getExistedProvinceAndCityStatistic();
        List<ExistedProvinceAndCity> lists = new ArrayList<>();
        String lastProvince = "";
        List<String> lastCities = new ArrayList<>();
        int flag = 0;
        for (Object[] o : infos) {
            String province = String.valueOf(o[0]);
            String city = String.valueOf(o[1]);
            if (flag == 0) {
                lastProvince = province;
                lastCities.add(city);
                flag = 1;
                continue;
            }
            if (!province.equals(lastProvince)) {
                lists.add(new ExistedProvinceAndCity(lastProvince, new ArrayList<>(lastCities)));
                lastProvince = province;
                lastCities.clear();
            }
            lastCities.add(city);
        }
        lists.add(new ExistedProvinceAndCity(lastProvince, lastCities));
        return existedProvinceAndCityRepository.saveAll(lists);
    }

    @Override
    public List<ExistedProvinceAndCity> getExistedProvinceAndCityStatistic() {
        return existedProvinceAndCityRepository.findAll();
    }
}
