package com.suda.tree.entity.mysql;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tree_grade_statistic")
public class TreeGradeStatistic {
    /**
     * 省（自治区、直辖市）
     */
    @Id
    @Field(name = "province")
    private String province;

    /**
     * 国家一级古树
     */
    @Field(name = "level1")
    private int level1;

    /**
     * 国家二级古树
     */
    @Field(name = "level2")
    private int level2;

    /**
     * 国家三级古树
     */
    @Field(name = "level3")
    private int level3;

    /**
     * 名木
     */
    @Field(name = "famous")
    private int famous;
}
