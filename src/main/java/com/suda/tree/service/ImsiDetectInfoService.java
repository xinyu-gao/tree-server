package com.suda.tree.service;

import com.suda.tree.entity.mysql.ImsiDetectInfo;
import com.suda.tree.entity.mysql.ImsiDetectInfoHistory;

import java.util.List;

public interface ImsiDetectInfoService {

    void saveImsiDetectInfo(ImsiDetectInfo imsiDetectInfo);

    ImsiDetectInfo getImsiDetectInfoByImsi(String imsi);

    void saveImsiDetectHInfoHistory(ImsiDetectInfoHistory imsiDetectInfoHistory);

    List<ImsiDetectInfoHistory> getImsiDetectInfoHistoriesByImsi(String imsi);

}
