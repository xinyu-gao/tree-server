package com.suda.tree.service;

import com.suda.tree.dto.result.LineDataResult;
import com.suda.tree.dto.result.PageResult;
import com.suda.tree.entity.mysql.ImsiInfoHistory;

public interface ImsiInfoService {
    LineDataResult getLineDataByIMSI(String imsi);

    PageResult<ImsiInfoHistory> getNodeHistoryInfoByImsi(String imsi, int page, int size);
}
