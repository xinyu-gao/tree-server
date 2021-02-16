package com.suda.tree.service.impl;

import cn.hutool.core.date.DateUtil;
import com.suda.tree.dao.TreeInfoRepository;
import com.suda.tree.dao.UploadHistoryRepository;
import com.suda.tree.dto.PageResult;
import com.suda.tree.entity.mongo.UploadHistory;
import com.suda.tree.entity.mysql.TreeGradeStatistic;
import com.suda.tree.entity.mysql.TreeInfo;
import com.suda.tree.service.StatisticService;
import com.suda.tree.service.TreeInfoService;
import com.suda.tree.util.CommonUtil;
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
    public List<TreeInfo> getTreeList(String city){
        return treeInfoRepository.findTreeInfoByLocationCity(city);
    }

    @Override
    public List<TreeInfo> getTreeListAll(){
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
}


