package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * 公告信息
 */
@Entity
@Data
@Table(name = "policies")
public class Policies {

    /**
     * 公告标题
     */
    @Id
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告推送人
     */
    private String publisher;

    /**
     * 公告推送时间
     */
    @Column(name = "publish_time")
    private String publishTime;

    /**
     * 公告链接
     */
    @Column(name = "publish_link")
    private String publish_link;
}
