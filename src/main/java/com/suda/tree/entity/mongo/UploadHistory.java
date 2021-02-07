package com.suda.tree.entity.mongo;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Document
@Data
public class UploadHistory {

    @Field(name = "tree_id")
    @Indexed(unique = true)
    @NotNull
    private String treeId;

    /**
     * 调查顺序号
     */
    @Field(name = "survey_number")
    private String surveyNumber;

    public UploadHistory(@NotNull String treeId, String uploadUser, String uploadDetail) {
        this.treeId = treeId;
//        this.surveyNumber = surveyNumber;
//        this.chineseName = chineseName;
        this.uploadUser = uploadUser;
        this.uploadDetail = uploadDetail;
        this.uploadTime = DateUtil.now();
    }

    /**
     * 中文名
     */
    @Field(name = "chinese_name")
    private String chineseName;

    @Field(name = "upload_time")
    private String uploadTime;

    @Field(name = "upload_user")
    private String uploadUser;

    @Field(name = "upload_detail")
    private String uploadDetail;

}
