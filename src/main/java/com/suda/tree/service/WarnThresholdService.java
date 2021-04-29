package com.suda.tree.service;

import com.suda.tree.entity.mysql.WarnThreshold;

public interface WarnThresholdService {

    WarnThreshold getWarnThreshold(String treeId);

    WarnThreshold setWarnThreshold(WarnThreshold w);
}
