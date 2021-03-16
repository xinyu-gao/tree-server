package com.suda.tree.entity.mysql;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据修改记录
 */
@Entity
@Data
@Table(name = "upload_history")
public class UploadHistory {

    public UploadHistory(String treeId, String uploadUser, String uploadDetail) {
        this.treeId = treeId;
        this.uploadUser = uploadUser;
        this.uploadDetail = uploadDetail;
        this.uploadTime = DateUtil.now();
    }

    public UploadHistory() {

    }

    @Id
    @Column(name = "tree_id")
    private String treeId;

    @Column(name = "survey_number")
    private String surveyNumber;

    @Column(name = "chinese_name")
    private String chineseName;

    @Column(name = "upload_time")
    private String uploadTime;

    @Column(name = "upload_user")
    private String uploadUser;

    @Column(name = "upload_detail")
    private String uploadDetail;
    
}
