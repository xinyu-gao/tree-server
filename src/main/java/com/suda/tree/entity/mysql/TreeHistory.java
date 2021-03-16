package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 古树历史信息
 */
@Data
@Entity
@Table(name = "tree_history")
public class TreeHistory {

    @Id
    @Column(name = "imsi")
    private String treeId;

    /**
     * 古树历史（300以内）
     */
    @Column(name = "history")
    private String history;

    public TreeHistory(@NotNull String treeId, @NotNull String history) {
        this.treeId = treeId;
        this.history = history;
    }

    public TreeHistory() {

    }
}