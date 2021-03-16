package com.suda.tree.entity.mysql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Entity
@Data
@Table(name = "policies")
public class Policies {

    @Id
    private String title;

    private String content;

    private String publisher;

    @Column(name = "publish_time")
    private String publishTime;

    @Column(name = "publish_link")
    private String publish_link;
}
