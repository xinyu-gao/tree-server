package com.suda.tree.service;

import com.suda.tree.dto.result.LineDataResult;

public interface ImsiInfoHistoryService {
    LineDataResult getLineDataByIMSI(String imsi);
}
