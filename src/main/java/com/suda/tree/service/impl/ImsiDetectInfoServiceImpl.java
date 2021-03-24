package com.suda.tree.service.impl;

import com.suda.tree.dao.ImsiDetectInfoHistoryRepository;
import com.suda.tree.dao.ImsiDetectInfoRepository;
import com.suda.tree.entity.mysql.ImsiDetectInfo;
import com.suda.tree.entity.mysql.ImsiDetectInfoHistory;
import com.suda.tree.service.ImsiDetectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImsiDetectInfoServiceImpl implements ImsiDetectInfoService {

    @Autowired
    private ImsiDetectInfoRepository imsiDetectInfoRepository;

    @Autowired
    private ImsiDetectInfoHistoryRepository imsiDetectInfoHistoryRepository;

    @Override
    @Async
    public void saveImsiDetectInfo(ImsiDetectInfo imsiDetectInfo) {
        imsiDetectInfoRepository.save(imsiDetectInfo);
    }

    @Override
    public ImsiDetectInfo getImsiDetectInfoByImsi(String imsi) {
        return imsiDetectInfoRepository.findById(imsi).orElseGet(ImsiDetectInfo::new);
    }

    @Override
    @Async
    public void saveImsiDetectHInfoHistory(ImsiDetectInfoHistory imsiDetectInfoHistory) {
        imsiDetectInfoHistoryRepository.save(imsiDetectInfoHistory);
    }

    @Override
    public List<ImsiDetectInfoHistory> getImsiDetectInfoHistoriesByImsi(String imsi) {
        return imsiDetectInfoHistoryRepository.findAllByImsi(imsi);
    }
}
