package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 树木等级统计（以省为单位统计）
 */
@Entity
@Data
@Table(name = "tree_grade_statistic")
public class TreeGradeStatistic {
    /**
     * 省（自治区、直辖市）
     */
    @Id
    @Column(name = "province")
    private String province;

    /**
     * 国家一级古树数量
     */
    @Column(name = "level1")
    private int level1;

    /**
     * 国家二级古树数量
     */
    @Column(name = "level2")
    private int level2;

    /**
     * 国家三级古树数量
     */
    @Column(name = "level3")
    private int level3;

    /**
     * 名木数量
     */
    @Column(name = "famous")
    private int famous;

    public TreeGradeStatistic(String province, int level1, int level2, int level3, int famous) {
        this.province = province;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.famous = famous;
    }

    public TreeGradeStatistic() {

    }
}
