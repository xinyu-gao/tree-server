package com.suda.tree.entity.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Policies {

    @Id
    private String title;

    private String content;

    private String publisher;

    private String publishTime;

    private String publish_link;
}
