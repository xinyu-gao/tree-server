package com.suda.tree.service.impl;

import com.suda.tree.dao.WarnThresholdRepository;
import com.suda.tree.entity.mysql.WarnThreshold;
import com.suda.tree.service.WarnThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarnThresholdServiceImpl implements WarnThresholdService {


    @Autowired
    private WarnThresholdRepository warnThresholdRepository;

    @Override
    public WarnThreshold getWarnThreshold(String treeId) {
        return warnThresholdRepository.findById(treeId).orElseGet(WarnThreshold::new);
    }

    @Override
    public WarnThreshold setWarnThreshold(WarnThreshold w) {
        return warnThresholdRepository.save(w);
    }
}
