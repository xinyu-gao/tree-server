package com.suda.tree.dto.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class GradeStatisticSQLResult implements Serializable {

    private String province;

    private String grade;

    private int count;

    public GradeStatisticSQLResult(String province, String grade, int count) {
        this.province = province;
        this.grade = grade;
        this.count = count;
    }
}
