package com.suda.tree.util;

import cn.hutool.core.date.DateUtil;
import com.suda.tree.dto.param.NodeParam;
import com.suda.tree.entity.mysql.ImsiInfo;
import com.suda.tree.entity.mysql.ImsiInfoHistory;

public class TransferUtil {
    public static ImsiInfo NodeParamToImsiInfo(NodeParam nodeParam){
        ImsiInfo imsiInfo = new ImsiInfo();
        imsiInfo.setImsi(nodeParam.getImsi());
        imsiInfo.setTemp(nodeParam.getTemp());
        imsiInfo.setHumidity(nodeParam.getHumidity());
        imsiInfo.setSlant(nodeParam.getSlant());
        imsiInfo.setOnline(true);
        imsiInfo.setSendTime(DateUtil.date());
        return imsiInfo;
    }
    public static ImsiInfoHistory NodeParamToImsiInfoHistory(NodeParam nodeParam){
        ImsiInfoHistory imsiInfoHistory = new ImsiInfoHistory();
        imsiInfoHistory.setImsi(nodeParam.getImsi());
        imsiInfoHistory.setTemp(nodeParam.getTemp());
        imsiInfoHistory.setHumidity(nodeParam.getHumidity());
        imsiInfoHistory.setSlant(nodeParam.getSlant());
        imsiInfoHistory.setSendTime(DateUtil.date());
        return imsiInfoHistory;
    }
}
