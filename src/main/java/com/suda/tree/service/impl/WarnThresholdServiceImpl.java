package com.suda.tree.service.impl;

import com.suda.tree.dao.WarnThresholdRepository;
import com.suda.tree.entity.mysql.WarnThreshold;
import com.suda.tree.service.WarnThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarnThresholdServiceImpl implements WarnThresholdService {

    /**
     * 只用一条数据，固定主键id
     */
    private Long warnId = 2L;

    @Autowired
    private WarnThresholdRepository warnThresholdRepository;

    @Override
    public WarnThreshold getWarnThreshold() {
        return warnThresholdRepository.findById(warnId).orElseGet(WarnThreshold::new);
    }

    @Override
    public WarnThreshold setWarnThreshold(WarnThreshold w) {
        w.setId(warnId);
        return warnThresholdRepository.save(w);
    }
}
