package com.suda.tree.service.impl;

import com.suda.tree.dao.TreeInfoRepository;
import com.suda.tree.dao.UploadHistoryRepository;
import com.suda.tree.dto.result.PageResult;
import com.suda.tree.entity.mysql.CityTreeCount;
import com.suda.tree.entity.mysql.TreeGradeStatistic;
import com.suda.tree.entity.mysql.TreeInfo;
import com.suda.tree.entity.mysql.UploadHistory;
import com.suda.tree.service.StatisticService;
import com.suda.tree.service.TreeInfoService;
import com.suda.tree.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TreeInfoServiceImpl implements TreeInfoService {
    @Autowired
    private TreeInfoRepository treeInfoRepository;
    @Autowired
    private UploadHistoryRepository uploadHistoryRepository;
    @Autowired
    private StatisticService statisticService;

    @Override
    public TreeInfo getTreeInfoByTreeId(String treeId) {
        Optional<TreeInfo> treeInfoOptional = treeInfoRepository.findByTreeId(treeId);
        return treeInfoOptional.orElseGet(TreeInfo::new);
    }

    @Override
    public TreeInfo saveTreeInfo(TreeInfo treeInfo, String username) {
        String detail = treeInfoRepository.findByTreeId(treeInfo.getTreeId()).isPresent() ? "修改数据" : "新增数据";
        UploadHistory uploadHistory = new UploadHistory(treeInfo.getTreeId(), username, detail);
        uploadHistoryRepository.save(uploadHistory);
        return treeInfoRepository.save(treeInfo);
    }

    @Override
    public List<TreeGradeStatistic> calculateGradeStatistic() {
        List<Object[]> objects = treeInfoRepository.getTreeGradeStatistic();
        List<TreeGradeStatistic> treeGradeStatisticList = sqlResultToTreeGradeStatisticList(objects);
        return statisticService.saveAll(treeGradeStatisticList);
    }

    @Override
    public PageResult<TreeInfo> getTreeList(int page, int size, int desc) {
        Sort.Order o1 = new Sort.Order(desc == 1 ? Sort.Direction.DESC : Sort.Direction.ASC, "treeId");
        Pageable pageable = PageRequest.of(page, size, Sort.by(o1));
        Page<TreeInfo> treeInfos = treeInfoRepository.findAll(pageable);
        return PageUtil.setResult(treeInfos);
    }

    @Override
    public PageResult<TreeInfo> getTreeListSorted(int page, int size, String[] keys, int asc) {
        List<Sort.Order> sorts = new ArrayList<>();
        for(String i : keys){
            sorts.add(new Sort.Order(asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, i));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sorts));
        Page<TreeInfo> treeInfos = treeInfoRepository.findAll(pageable);
        return PageUtil.setResult(treeInfos);
    }

    @Override
    public List<TreeInfo> getTreeList(String city) {
        return treeInfoRepository.findTreeInfoByLocationCity(city);
    }

    @Override
    public List<TreeInfo> getTreeListAll() {
        return treeInfoRepository.findAll();
    }

    private List<TreeGradeStatistic> sqlResultToTreeGradeStatisticList(List<Object[]> objects) {
        Map<String, int[]> mapStatistic = new HashMap<>();
        for (Object[] o : objects) {
            String key = (String) o[0];
            if (!mapStatistic.containsKey(key)) mapStatistic.put(key, new int[]{0, 0, 0, 0});
            int[] value = mapStatistic.get(key);
            if (("国家一级古树").equals(o[1])) {
                value[0] = Integer.parseInt(o[2].toString());
            } else if (("国家二级古树").equals(o[1])) {
                value[1] = Integer.parseInt(o[2].toString());
            } else if (("国家三级古树").equals(o[1])) {
                value[2] = Integer.parseInt(o[2].toString());
            } else if (("名木").equals(o[1])) {
                value[3] = Integer.parseInt(o[2].toString());
            }
            mapStatistic.put(key, value);
        }
        List<TreeGradeStatistic> treeGradeList = new ArrayList<>();
        mapStatistic.forEach((key, value) -> treeGradeList.add(new TreeGradeStatistic(key, value[0], value[1], value[2], value[3])));
        return treeGradeList;
    }

    @Override
    public List<CityTreeCount> genProvinceAndCityTreeCount() {
        return treeInfoRepository.getProvinceAndCityTreeCount()
                .stream()
                .map(item -> {
                    String city = isMunicipality(item[0]) || isInHK_TW_M(item[0]) ?
                            String.valueOf(item[0]).substring(0, 2) : String.valueOf(item[1]);
                    return new CityTreeCount(city, Integer.parseInt(String.valueOf(item[2])));
                })
                .collect(Collectors.toList());
    }


    /**
     * 判断是否是直辖市
     */
    public boolean isMunicipality(Object o) {
        String city = String.valueOf(o);
        return city.startsWith("北京") || city.startsWith("天津") || city.startsWith("上海") || city.startsWith("重庆");
    }

    /**
     * 判断是否是港澳台城市
     */
    public boolean isInHK_TW_M(Object o) {
        String city = String.valueOf(o);
        return city.startsWith("香港") || city.startsWith("澳门") || city.startsWith("台湾");
    }

}


